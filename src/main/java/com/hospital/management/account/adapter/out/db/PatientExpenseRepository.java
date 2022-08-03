package com.hospital.management.account.adapter.out.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientExpenseRepository extends JpaRepository<PatientExpensesEntity, Long> {

    Page<PatientExpensesEntity> findByPatientMobileNumber(String mobile, Pageable pageable);
}
