package com.hospital.management.account.adapter.in.web;

import com.hospital.management.account.application.ports.in.AccountService;
import com.hospital.management.account.domain.requests.AccountRequest;
import com.hospital.management.account.domain.requests.PasswordRequest;
import com.hospital.management.account.domain.responses.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.websocket.server.PathParam;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping(value = "signup")
    public String createAccount(@Valid @RequestBody AccountRequest accountRequest) {
        return accountService.createEmployeeAccount(accountRequest);
    }

    @GetMapping(value = "login/{employeeId}")
    public EmployeeResponse createAccount(@PathParam(value = "employeeId") @NotBlank(message = "EmployeeId is required") String employeeId,
                                          @RequestParam @NotBlank(message = "Password is required") String password) {
        return accountService.login(employeeId, password);
    }

    @PutMapping(value = "update/password/{employeeId}")
    public String updatePassword(@Valid @RequestBody PasswordRequest request) {
        return accountService.updatePassword(request.getEmployeeId(), request.getPassword());
    }
}
