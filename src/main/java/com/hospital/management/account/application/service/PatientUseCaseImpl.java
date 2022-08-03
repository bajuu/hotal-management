package com.hospital.management.account.application.service;

import com.hospital.management.account.adapter.out.db.PatientEntity;
import com.hospital.management.account.adapter.out.db.PatientExpenseRepository;
import com.hospital.management.account.adapter.out.db.PatientExpensesEntity;
import com.hospital.management.account.adapter.out.db.PatientRepository;
import com.hospital.management.account.adapter.out.enums.PatientStatus;
import com.hospital.management.account.application.ports.out.PatientUseCase;
import com.hospital.management.account.domain.requests.PatientAdmitRequest;
import com.hospital.management.account.domain.requests.PatientExpenseRequest;
import com.hospital.management.exceptions.CustomException;
import com.hospital.management.notification.application.ports.out.NotificationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hospital.management.utils.ApplicationConstants.DISCHARGE_MESSAGE;

@Service
@RequiredArgsConstructor
public class PatientUseCaseImpl implements PatientUseCase {

    private final PatientRepository patientRepository;
    private final PatientExpenseRepository patientExpenseRepository;
    private final NotificationUseCase notificationUseCase;

    @Override
    public PatientEntity getDetails(String mobileNumber) {
        Optional<PatientEntity> isPatientAdmitted = patientRepository.findByMobileNumber(mobileNumber);
        if (isPatientAdmitted.isEmpty())
            throw new CustomException("Patient with mobile number not admitted", HttpStatus.BAD_REQUEST);
        return isPatientAdmitted.get();
    }

    @Override
    public String admit(PatientAdmitRequest request) {
        Optional<PatientEntity> isPatientAdmitted = patientRepository.findByMobileNumber(request.getContactNumber());
        if (isPatientAdmitted.isPresent() && isPatientAdmitted.get().getPatientStatus().equals(PatientStatus.ADMITTED)) {
            throw new CustomException("This Patient is already admitted", HttpStatus.BAD_REQUEST);
        }
        if (isPatientAdmitted.isPresent()) {
            PatientEntity patient = isPatientAdmitted.get();
            patient.setAdmitDate(Date.valueOf(LocalDate.now()));
            patientRepository.save(patient);
            saveExpenses(request, patient);
            return "Patient readmitted";
        }
        PatientEntity patient = patientRepository.save(request.toEntity());
        saveExpenses(request, patient);
        return "Patient admitted";
    }

    @Override
    public String dischargePatient(String mobileNumber) {
        Optional<PatientEntity> isPatientAdmitted = patientRepository.findByMobileNumber(mobileNumber);
        if (isPatientAdmitted.isEmpty())
            throw new CustomException("Patient with mobile number not admitted", HttpStatus.BAD_REQUEST);
        //todo check for payment due, if due not clear return due amount
        PatientEntity patient = isPatientAdmitted.get();
        patient.setPatientStatus(PatientStatus.DISCHARGED);
        patientRepository.save(patient);
        notificationUseCase.sendNotification(DISCHARGE_MESSAGE, patient.getMobileNumber());
        return patient.getName() + " has been discharged.";
    }

    @Override
    public Page<PatientEntity> getPatientsByStatus(PatientStatus status, Integer page) {
        return patientRepository.findByPatientStatus(status, PageRequest.of(page, 10));
    }

    @Override
    public Page<PatientExpensesEntity> getPatientsExpenseList(String mobileNumber, Integer pageNumber) {
        return patientExpenseRepository.findByPatientMobileNumber(mobileNumber, PageRequest.of(pageNumber, 10));
    }

    @Override
    public String listNewExpense(PatientExpenseRequest request) {
        Optional<PatientEntity> isPatientAdmitted = patientRepository.findByMobileNumber(request.getMobileNumber());
        if (isPatientAdmitted.isEmpty())
            throw new CustomException("Patient with mobile number not admitted", HttpStatus.BAD_REQUEST);
        PatientEntity patient = isPatientAdmitted.get();
        PatientExpensesEntity patientExpensesEntity = request.toEntity(patient);
        patientExpenseRepository.save(patientExpensesEntity);
        return "Expense added successfully!";
    }

    private void saveExpenses(PatientAdmitRequest request, PatientEntity patient) {
        if (request.getExpenses() != null) {
            List<PatientExpensesEntity> expensesEntities = request.getExpenses().stream().
                    map(expense -> expense.toEntity(patient)).
                    collect(Collectors.toList());
            patientExpenseRepository.saveAll(expensesEntities);
        }
    }
}
