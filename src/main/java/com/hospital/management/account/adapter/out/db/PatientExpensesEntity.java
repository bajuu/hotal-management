package com.hospital.management.account.adapter.out.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Data
@Table(name = "patient_expenses")
public class PatientExpensesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal expenses;

    private String description;

    private Date expenseDate;

    private String patient_mobile_number;

    @ManyToOne
    @JoinColumn(name = "mobile_number")
    @JsonIgnore
    PatientEntity patient;
}
