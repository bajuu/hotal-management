package com.hospital.management.account.domain.requests;

import com.hospital.management.account.adapter.out.db.PatientEntity;
import com.hospital.management.account.adapter.out.db.PatientExpensesEntity;
import com.hospital.management.account.adapter.out.enums.PatientStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PatientAdmitRequest {

    @NotEmpty(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "The length of full name must be between 2 and 100 characters.")
    String patientName;

    Integer patientAge;

    @NotNull(message = "Please mention room number.")
    Integer roomNumber;

    @NotNull(message = "Please mention floor number.")
    Integer floorNumber;

    @NotBlank(message = "Doctor name is mandatory.")
    String doctorName;

    @NotBlank(message = "Contact number is mandatory.")
    String contactNumber;

    Date admitDate;

    Date dischargeDate;

    List<PatientExpenseRequest> expenses;

    public PatientEntity toEntity() {
        PatientEntity patient = new PatientEntity();
        patient.setName(this.patientName);
        patient.setAge(this.patientAge);
        patient.setAdmitDate(this.admitDate == null ? Date.valueOf(LocalDate.now()) : this.admitDate);
        patient.setMobileNumber(this.contactNumber);
        patient.setPatientStatus(PatientStatus.ADMITTED);
        return patient;
    }
}
