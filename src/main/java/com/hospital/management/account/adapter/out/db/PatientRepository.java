package com.hospital.management.account.adapter.out.db;

import com.hospital.management.account.adapter.out.enums.PatientStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    Optional<PatientEntity> findByMobileNumber(String mobileNumber);

    Page<PatientEntity> findByPatientStatus(PatientStatus status, Pageable pageRequest);

    @Query(value = "SELECT patient_expenses FROM patient WHERE mobileNumber = ?1", nativeQuery = true)
    Page<PatientExpensesEntity> findPatientExpenses(String mobileNumber, Pageable pageRequest);
}
