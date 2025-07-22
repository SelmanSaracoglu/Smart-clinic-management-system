package com.selman.scms.repository;

import com.selman.scms.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Hastayı e-posta adresiyle getirir
    Optional<Patient> findByEmail(String email);

    // Hastayı telefon numarasıyla getirir
    Optional<Patient> findByPhone(String phone);
}
