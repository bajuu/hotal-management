package com.hospital.management.account.application.ports.out;

import com.hospital.management.account.adapter.out.db.PatientEntity;
import com.hospital.management.account.adapter.out.db.PatientExpensesEntity;
import com.hospital.management.account.adapter.out.enums.PatientStatus;
import com.hospital.management.account.domain.requests.PatientAdmitRequest;
import com.hospital.management.account.domain.requests.PatientExpenseRequest;
import org.springframework.data.domain.Page;

public interface PatientUseCase {
    PatientEntity getDetails(String mobileNumber);

    String admit(PatientAdmitRequest request);

    String dischargePatient(String mobileNumber);

    Page<PatientEntity> getPatientsByStatus(PatientStatus status, Integer pageNumber);

    Page<PatientExpensesEntity> getPatientsExpenseList(String mobileNumber, Integer pageNumber);

    String listNewExpense(PatientExpenseRequest request);
}
