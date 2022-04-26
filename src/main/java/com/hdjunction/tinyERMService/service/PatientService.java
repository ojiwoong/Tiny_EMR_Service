package com.hdjunction.tinyERMService.service;

import com.hdjunction.tinyERMService.dto.PatientCreateRequest;
import com.hdjunction.tinyERMService.dto.PatientResponse;
import com.hdjunction.tinyERMService.dto.PatientUpdateRequest;
import com.hdjunction.tinyERMService.entity.Patient;

public interface PatientService {
    // 환자 등록
    Patient createPatient(PatientCreateRequest createRequest);

    // 환자 수정
    Patient updatePatient(Long patientId, PatientUpdateRequest patientUpdateRequest);

    // 환자 삭제
    void deletePatient(Long patientId);

    // 환자 id 조회
    PatientResponse getPatient(Long patientId);

    // 환자 등록번호 생성
    String createRegistrationNumber(Long hospitalId);
}
