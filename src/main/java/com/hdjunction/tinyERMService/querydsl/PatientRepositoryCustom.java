package com.hdjunction.tinyERMService.querydsl;

import com.hdjunction.tinyERMService.dto.PatientSearchKeyword;
import com.hdjunction.tinyERMService.entity.Patient;

import java.util.List;

public interface PatientRepositoryCustom {
    List<Patient> findByNameAndRegistrationNumberAndDateBirth(PatientSearchKeyword patientSearchKeyword);
}
