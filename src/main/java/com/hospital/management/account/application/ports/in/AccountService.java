package com.hospital.management.account.application.ports.in;

import com.hospital.management.account.domain.requests.AccountRequest;
import com.hospital.management.account.domain.responses.EmployeeResponse;

public interface AccountService {
    String createEmployeeAccount(AccountRequest accountRequest);

    EmployeeResponse login(String employeeId, String password);

    String updatePassword(String employeeId, String password);
}
