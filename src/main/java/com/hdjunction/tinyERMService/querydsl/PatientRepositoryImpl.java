package com.hdjunction.tinyERMService.querydsl;

import com.hdjunction.tinyERMService.dto.PatientSearchKeyword;
import com.hdjunction.tinyERMService.entity.Patient;
import com.hdjunction.tinyERMService.entity.QPatient;
import com.hdjunction.tinyERMService.querydsl.PatientRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PatientRepositoryImpl implements PatientRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Patient> findByNameAndRegistrationNumberAndDateBirth(PatientSearchKeyword patientSearchKeyword) {

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
}
