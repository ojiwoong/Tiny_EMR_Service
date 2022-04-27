package com.hdjunction.tinyERMService.querydsl;

import com.hdjunction.tinyERMService.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientRepositoryCustom {
    // 환자 목록 동적 검색 조회 (환자명, 환자등록번호, 생년월일)
    List<Patient> findByAllSearchKeyword(PatientSearchKeyword patientSearchKeyword);

    // 환자 목록 동적 검색 페이징 조회 (환자명, 환자등록번호, 생년월일)
    Page<Patient> findByAllSearchKeyword(PatientSearchKeyword patientSearchKeyword, Pageable pageable);
}
