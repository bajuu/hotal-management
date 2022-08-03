package com.hospital.management.account.adapter.out.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hospital.management.account.adapter.out.enums.PatientStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table(name = "patient")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    private Date admitDate;

    private Date dischargeDate;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<PatientExpensesEntity> expenses;

    @Enumerated(EnumType.STRING)
    private PatientStatus patientStatus;
}
