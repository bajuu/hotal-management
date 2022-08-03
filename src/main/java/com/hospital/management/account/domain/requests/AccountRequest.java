package com.hospital.management.account.domain.requests;

import com.hospital.management.account.adapter.out.db.EmployeeEntity;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

@Data
public class AccountRequest {

    @NotEmpty(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "The length of full name must be between 2 and 100 characters.")
    String nameOfEmployee;

    @NotNull(message = "DOB is mandatory")
    @Past(message = "The date of birth must be in the past.")
    @Valid
    Date dateOfBirth;

    Date joiningDate;

    @NotEmpty(message = "Department is mandatory")
    @Valid
    String department;

    String emailAddress;

    @NotBlank(message = "Mobile number is mandatory")
    String mobileNumber;

    public EmployeeEntity toEntity() {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setName(nameOfEmployee);
        employee.setDateOfBirth(dateOfBirth);
        employee.setDateOfJoining(joiningDate == null ? Date.valueOf(LocalDate.now()) : joiningDate);
        employee.setDepartment(department);
        employee.setEmail(emailAddress);
        employee.setMobileNumber(mobileNumber);
        employee.setEmployeeId(generateEmployeeId());
        return employee;
    }

    private String generateEmployeeId() {
       /* We can change this logic in another way like getting last employee id from db and increment by one
        and prefix of this employee id can be fetch from db as well. Making it simple for now.*/
        Random r = new Random(System.currentTimeMillis());
        return "EMP-" + (10000 + r.nextInt(20000));
    }
}
