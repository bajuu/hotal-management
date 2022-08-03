package com.hospital.management.account.application.ports.out;

import com.hospital.management.account.domain.requests.AccountRequest;
import com.hospital.management.account.domain.responses.EmployeeResponse;

public interface EmployeeUseCase {
    String createNewEmployee(AccountRequest accountRequest);

    EmployeeResponse validateEmployeeCredentials(String employeeId, String password);

    String updatePassword(String employeeId, String password);
}
