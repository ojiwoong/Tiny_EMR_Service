package com.hdjunction.tinyERMService.service;

import com.hdjunction.tinyERMService.dto.*;
import com.hdjunction.tinyERMService.querydsl.PatientSearchKeyword;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {
    // 환자 등록
    PatientResponse createPatient(PatientCreateRequest createRequest);

    // 환자 수정
    PatientResponse updatePatient(Long patientId, PatientUpdateRequest patientUpdateRequest);

    // 환자 삭제
    void deletePatient(Long patientId);

    // 환자 id 조회
    PatientResponse getPatient(Long patientId);

    // 전체 환자 목록 조회
    Page<PatientResponse> getAllPatient(PatientSearchKeyword patientSearchKeyword, Pageable pageable);

    // 환자 등록번호 생성
    String createRegistrationNumber(Long hospitalId);
}
