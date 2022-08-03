package com.hospital.management.account.domain.requests;

import com.hospital.management.account.adapter.out.db.PatientEntity;
import com.hospital.management.account.adapter.out.db.PatientExpensesEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Data
public class PatientExpenseRequest {

    @NotBlank(message = "Please mention Mobile Number of patient")
    private String mobileNumber;

    @NotNull(message = "Expense amount is required")
    private BigDecimal amount;

    private Date expenseDate;

    private String description;

    public PatientExpensesEntity toEntity(PatientEntity patient) {
        PatientExpensesEntity patientExpensesEntity = new PatientExpensesEntity();
        patientExpensesEntity.setExpenseDate(expenseDate != null ? expenseDate : java.sql.Date.valueOf(LocalDate.now()));
        patientExpensesEntity.setDescription(this.description);
        patientExpensesEntity.setExpenses(this.amount);
        patientExpensesEntity.setPatient(patient);
        patientExpensesEntity.setPatient_mobile_number(patient.getMobileNumber());
        return patientExpensesEntity;
    }

}
