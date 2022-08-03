package com.hospital.management.account.domain.responses;

import lombok.Builder;
import lombok.Data;

@Data
public class EmployeeResponse {
    private String employeeId;

    private String name;

    private String department;
}
