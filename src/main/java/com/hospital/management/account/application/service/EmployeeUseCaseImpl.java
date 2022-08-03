package com.hospital.management.account.application.service;

import com.hospital.management.account.adapter.out.db.EmployeeEntity;
import com.hospital.management.account.adapter.out.db.EmployeeRepository;
import com.hospital.management.account.application.ports.out.EmployeeUseCase;
import com.hospital.management.account.domain.requests.AccountRequest;
import com.hospital.management.account.domain.responses.EmployeeResponse;
import com.hospital.management.exceptions.CustomException;
import com.hospital.management.notification.application.ports.out.NotificationUseCase;
import com.hospital.management.utils.PasswordEncryptor;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.hospital.management.utils.ApplicationConstants.WELCOME_MESSAGE;

@Service
@RequiredArgsConstructor
public class EmployeeUseCaseImpl implements EmployeeUseCase {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncryptor passwordEncryptor;
    private final NotificationUseCase notificationUseCase;

    @Override
    public String createNewEmployee(AccountRequest accountRequest) {
        String systemGeneratedPassword = RandomString.make(5);
        EmployeeEntity toEntity = accountRequest.toEntity();
        toEntity.setLoginPassword(passwordEncryptor.encrypt(systemGeneratedPassword));
        EmployeeEntity employee = employeeRepository.save(toEntity);
        notificationUseCase.sendNotification(String.format(WELCOME_MESSAGE, employee.getName(), employee.getEmployeeId(), systemGeneratedPassword), employee.getMobileNumber());
        return employee.getEmployeeId();
    }

    @Override
    public EmployeeResponse validateEmployeeCredentials(String employeeId, String password) {
        Optional<EmployeeEntity> employeeOptional = employeeRepository.findByEmployeeId(employeeId);
        if (employeeOptional.isEmpty())
            throw new CustomException("No Employee found with employee id " + employeeId, HttpStatus.BAD_REQUEST);
        EmployeeEntity employee = employeeOptional.get();
        if (!passwordEncryptor.encrypt(password).equals(employee.getLoginPassword())) {
            throw new CustomException("Wrong Password", HttpStatus.BAD_REQUEST);
        }
        return employee.toResponse();
    }

    @Override
    public String updatePassword(String employeeId, String password) {
        Optional<EmployeeEntity> employeeOptional = employeeRepository.findByEmployeeId(employeeId);
        if (employeeOptional.isEmpty())
            throw new CustomException("No Employee found with employee id " + employeeId, HttpStatus.BAD_REQUEST);
        EmployeeEntity employee = employeeOptional.get();
        employee.setLoginPassword(passwordEncryptor.encrypt(password));
        employeeRepository.save(employee);
        return "Password updated successfully!";
    }
}
