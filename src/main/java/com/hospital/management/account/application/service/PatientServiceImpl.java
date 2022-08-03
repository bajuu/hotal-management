package com.hospital.management.account.application.service;

import com.hospital.management.account.adapter.out.db.PatientEntity;
import com.hospital.management.account.adapter.out.db.PatientExpensesEntity;
import com.hospital.management.account.adapter.out.enums.PatientStatus;
import com.hospital.management.account.application.ports.in.PatientServices;
import com.hospital.management.account.application.ports.out.PatientUseCase;
import com.hospital.management.account.domain.requests.PatientAdmitRequest;
import com.hospital.management.account.domain.requests.PatientExpenseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientServices {

    private final PatientUseCase patientUseCase;

    @Override
    public String admitPatient(PatientAdmitRequest request) {
        return patientUseCase.admit(request);
    }

    @Override
    public PatientEntity getPatientDetails(String mobileNumber) {
        return patientUseCase.getDetails(mobileNumber);
    }

    @Override
    public String dischargePatient(String mobileNumber) {
        return patientUseCase.dischargePatient(mobileNumber);
    }

    @Override
    public Page<PatientEntity> getPatientsByStatus(PatientStatus status, Integer pageNumber) {
        return patientUseCase.getPatientsByStatus(status, pageNumber);
    }

    @Override
    public Page<PatientExpensesEntity> getPatientsExpenseList(String mobileNumber, Integer pageNumber) {
        return patientUseCase.getPatientsExpenseList(mobileNumber, pageNumber);
    }

    @Override
    public String listNewExpense(PatientExpenseRequest request) {
        return patientUseCase.listNewExpense(request);
    }
}
