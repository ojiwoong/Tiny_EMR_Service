package com.hdjunction.tinyERMService.repository;

import com.hdjunction.tinyERMService.entity.Patient;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query(value = "select registration_number from patient p where p.hospital_id = ?1 order by registration_number desc limit 1", nativeQuery = true)
    String getMaxRegistrationNumber(Long hospitalId);

    @Override
    @EntityGraph(attributePaths = {"visitList"})
    List<Patient> findAll();
}
