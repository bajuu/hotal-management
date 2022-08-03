package com.hospital.management.account.application.service;

import com.hospital.management.account.application.ports.in.AccountService;
import com.hospital.management.account.application.ports.out.EmployeeUseCase;
import com.hospital.management.account.domain.requests.AccountRequest;
import com.hospital.management.account.domain.responses.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final EmployeeUseCase employeeUseCase;

    @Override
    public String createEmployeeAccount(AccountRequest accountRequest) {
        return employeeUseCase.createNewEmployee(accountRequest);
    }

    @Override
    public EmployeeResponse login(String employeeId, String password) {
        return employeeUseCase.validateEmployeeCredentials(employeeId, password);
    }

    @Override
    public String updatePassword(String employeeId, String password) {
        return employeeUseCase.updatePassword(employeeId, password);
    }
}
