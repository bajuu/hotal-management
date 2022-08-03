package com.hospital.management.account.domain.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PasswordRequest {
    @NotBlank(message = "EmployeeId is required!")
    private String employeeId;

    @NotBlank(message = "Password is required!")
    private String password;
}
