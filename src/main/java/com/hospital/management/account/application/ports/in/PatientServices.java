package com.hospital.management.account.application.ports.in;

import com.hospital.management.account.adapter.out.db.PatientEntity;
import com.hospital.management.account.adapter.out.db.PatientExpensesEntity;
import com.hospital.management.account.adapter.out.enums.PatientStatus;
import com.hospital.management.account.domain.requests.PatientAdmitRequest;
import com.hospital.management.account.domain.requests.PatientExpenseRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

public interface PatientServices {
    String admitPatient(PatientAdmitRequest request);

    PatientEntity getPatientDetails(String mobileNumber);

    String dischargePatient(String mobileNumber);

    Page<PatientEntity> getPatientsByStatus(PatientStatus status, Integer pageNumber);

    Page<PatientExpensesEntity> getPatientsExpenseList(String mobileNumber, Integer pageNumber);

    String listNewExpense(PatientExpenseRequest request);
}
