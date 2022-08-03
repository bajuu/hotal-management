package com.hospital.management.account.adapter.in.web;

import com.hospital.management.account.adapter.out.db.PatientEntity;
import com.hospital.management.account.adapter.out.db.PatientExpensesEntity;
import com.hospital.management.account.adapter.out.enums.PatientStatus;
import com.hospital.management.account.application.ports.in.PatientServices;
import com.hospital.management.account.domain.requests.PatientAdmitRequest;
import com.hospital.management.account.domain.requests.PatientExpenseRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/patient")
public class PatientController {
    private final PatientServices patientServices;

    @PostMapping(value = "admit")
    public String createAccount(@Valid @RequestBody PatientAdmitRequest request) {
        return patientServices.admitPatient(request);
    }

    @GetMapping(value = "details")
    public PatientEntity createAccount(@RequestParam String mobileNumber) {
        return patientServices.getPatientDetails(mobileNumber);
    }

    @PutMapping(value = "discharge")
    public String dischargePatient(@RequestParam String mobileNumber) {
        return patientServices.dischargePatient(mobileNumber);
    }

    @GetMapping(value = "/data")
    public Page<PatientEntity> getPatientsByStatus(@RequestParam PatientStatus status,
                                                   @RequestParam Integer pageNumber) {
        return patientServices.getPatientsByStatus(status, pageNumber);
    }

    @GetMapping(value = "/expenses/list")
    public Page<PatientExpensesEntity> getPatientsExpenseList(@RequestParam String mobileNumber,
                                                              @RequestParam Integer pageNumber) {
        return patientServices.getPatientsExpenseList(mobileNumber, pageNumber);
    }

    @PostMapping(value = "/expenses/add")
    public String addNewExpense(@RequestBody PatientExpenseRequest request) {
        return patientServices.listNewExpense(request);
    }
}
