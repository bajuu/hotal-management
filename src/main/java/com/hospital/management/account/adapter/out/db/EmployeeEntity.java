package com.hospital.management.account.adapter.out.db;

import com.hospital.management.account.domain.responses.EmployeeResponse;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@Data
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date dateOfBirth;

    private String employeeId;

    private String loginPassword;

    private String email;

    private String mobileNumber;

    private String department;

    private Date dateOfJoining;


    public EmployeeResponse toResponse() {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployeeId(this.employeeId);
        employeeResponse.setName(this.name);
        employeeResponse.setDepartment(this.department);
        return employeeResponse;
    }
}
