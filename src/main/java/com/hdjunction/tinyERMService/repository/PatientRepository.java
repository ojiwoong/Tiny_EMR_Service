package com.hdjunction.tinyERMService.repository;

import com.hdjunction.tinyERMService.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
