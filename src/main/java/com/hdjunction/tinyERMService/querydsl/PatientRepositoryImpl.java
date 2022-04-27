package com.hdjunction.tinyERMService.querydsl;

import com.hdjunction.tinyERMService.entity.Patient;
import com.hdjunction.tinyERMService.entity.QPatient;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PatientRepositoryImpl implements PatientRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Patient> findByAllSearchKeyword(PatientSearchKeyword patientSearchKeyword) {

        QPatient patient = QPatient.patient;
        BooleanBuilder builder = new BooleanBuilder();

        String name = patientSearchKeyword.getName();
        String registrationNumber = patientSearchKeyword.getRegistrationNumber();
        String dateBirth = patientSearchKeyword.getDateBirth();

        // 환자명 검색
        if(!StringUtils.isNullOrEmpty(name)) {
            builder.and(patient.name.eq(name));
        }

        // 환자등록번호 검색
        if(!StringUtils.isNullOrEmpty(registrationNumber)) {
            builder.and(patient.registrationNumber.eq(registrationNumber));
        }

        // 환자 생년월일 검색
        if(!StringUtils.isNullOrEmpty(dateBirth)) {
            builder.and(patient.dateBirth.eq(dateBirth));
        }

        return queryFactory
                .selectFrom(patient)
                .where(builder)
                .fetch();
    }

    @Override
    public Page<Patient> findByAllSearchKeyword(PatientSearchKeyword patientSearchKeyword, Pageable pageable) {
        QPatient patient = QPatient.patient;
        BooleanBuilder builder = new BooleanBuilder();

        String name = patientSearchKeyword.getName();
        String registrationNumber = patientSearchKeyword.getRegistrationNumber();
        String dateBirth = patientSearchKeyword.getDateBirth();

        // 환자명 검색
        if(!StringUtils.isNullOrEmpty(name)) {
            builder.and(patient.name.eq(name));
        }

        // 환자등록번호 검색
        if(!StringUtils.isNullOrEmpty(registrationNumber)) {
            builder.and(patient.registrationNumber.eq(registrationNumber));
        }

        // 환자 생년월일 검색
        if(!StringUtils.isNullOrEmpty(dateBirth)) {
            builder.and(patient.dateBirth.eq(dateBirth));
        }

        List<Patient> content = queryFactory
                .selectFrom(patient)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        queryFactory
                .selectFrom(patient)
                .where(builder)
                .fetch();

        return new PageImpl(content, pageable, content.size());
    }
}
