package com.hdjunction.tinyERMService.service;

import com.hdjunction.tinyERMService.dto.*;
import com.hdjunction.tinyERMService.entity.Hospital;
import com.hdjunction.tinyERMService.entity.Patient;
import com.hdjunction.tinyERMService.entity.Visit;
import com.hdjunction.tinyERMService.querydsl.PatientRepositoryCustom;
import com.hdjunction.tinyERMService.querydsl.PatientRepositoryImpl;
import com.hdjunction.tinyERMService.querydsl.PatientSearchKeyword;
import com.hdjunction.tinyERMService.repository.HospitalRepository;
import com.hdjunction.tinyERMService.repository.PatientRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@Import({PatientServiceImpl.class, PatientRepository.class, HospitalRepository.class, PatientRepositoryImpl.class})
public class PatientServiceImplTest {
    Logger log = (Logger) LoggerFactory.getLogger(PatientServiceImplTest.class);

    @MockBean
    PatientRepository patientRepository;

    @MockBean
    HospitalRepository hospitalRepository;

    @MockBean
    PatientRepositoryImpl patientRepositoryImpl;

    @Autowired
    PatientServiceImpl patientService;

    @Test
    @DisplayName("환자등록번호 생성 테스트")
    public void createRegistrationNumber() {
        // given
        String maxRegistrationNumber = "202200032";
        String expectRegistrationNumber = "202200033";

        Mockito.when(patientRepository.getMaxRegistrationNumber(2L))
                .thenReturn(maxRegistrationNumber);

        // when
        String createdRegistrationNumber = patientService.createRegistrationNumber(2L);

        log.info("생성된 환자등록번호 => " + createdRegistrationNumber);

        // then
        assertEquals(expectRegistrationNumber, createdRegistrationNumber);
    }

    @Test
    @DisplayName("환자 등록 테스트")
    public void createPatient() {
        Hospital hospital = Hospital.builder()
                            .id(1L)
                            .name("서울병원")
                            .nursingInstitutionNumber("1")
                            .directorName("김서울")
                            .build();

        Patient expectPatient = Patient.builder()
                                .id(1L)
                                .hospital(hospital)
                                .name("오지웅")
                                .genderCode("M")
                                .dateBirth("1994-04-12")
                                .registrationNumber("202200001")
                                .mobilePhoneNumber("010-1234-1234")
                                .build();

        PatientCreateRequest patientCreateRequest = PatientCreateRequest.builder()
                                                                        .hospitalId(1L)
                                                                        .name("오지웅")
                                                                        .genderCode("M")
                                                                        .dateBirth("1994-04-12")
                                                                        .mobilePhoneNumber("010-1234-1234")
                                                                        .build();

        // given
        Mockito.when(patientRepository.save(Patient.builder()
                                                    .id(null)
                                                    .hospital(hospital)
                                                    .name("오지웅")
                                                    .genderCode("M")
                                                    .registrationNumber("202200001")
                                                    .dateBirth("1994-04-12")
                                                    .mobilePhoneNumber("010-1234-1234")
                                                    .build()
                ))
                .thenReturn(Patient.builder()
                                    .id(1L)
                                    .hospital(hospital)
                                    .name("오지웅")
                                    .genderCode("M")
                                    .dateBirth("1994-04-12")
                                    .registrationNumber("202200001")
                                    .mobilePhoneNumber("010-1234-1234")
                                    .build());

        Mockito.when(hospitalRepository.findById(1L))
                .thenReturn(Optional.ofNullable(hospital));

        // when
        PatientCreateResponse cratedPatient = (PatientCreateResponse)patientService.createPatient(patientCreateRequest);

        log.info("생성된 환자 => " + cratedPatient);

        // then
        assertEquals(cratedPatient.getId(), 1L);
        assertEquals(cratedPatient.getName(), "오지웅");
        assertEquals(cratedPatient.getGenderCode(), "M");
        assertEquals(cratedPatient.getRegistrationNumber(), "202200001");
        assertEquals(cratedPatient.getDateBirth(), "1994-04-12");
        assertEquals(cratedPatient.getMobilePhoneNumber(), "010-1234-1234");
    }

    @Test
    @DisplayName("환자 수정 테스트")
    public void updatePatient() {
        PatientUpdateRequest patientUpdateRequest = PatientUpdateRequest.builder()
                .name("권혜원")
                .genderCode("F")
                .dateBirth("1993-04-16")
                .mobilePhoneNumber("010-4321-4321")
                .build();

        Hospital hospital = Hospital.builder()
                .id(2L)
                .name("인천병원")
                .nursingInstitutionNumber("2")
                .directorName("이인천")
                .build();

        // given
        // 수정될 환자 mock 객체 생성
        Mockito.when(patientRepository.findById(2L))
                .thenReturn(Optional.ofNullable(Patient.builder()
                        .id(2L)
                        .hospital(hospital)
                        .name("오지웅")
                        .genderCode("M")
                        .registrationNumber("202200001")
                        .dateBirth("1994-04-12")
                        .mobilePhoneNumber("010-1234-1234")
                        .build()));

        // 수정 완료된 환자 mock 객체 생성
        Mockito.when(patientRepository.save(Patient.builder()
                        .id(2L)
                        .hospital(hospital)
                        .name("권혜원")
                        .genderCode("F")
                        .registrationNumber("202200001")
                        .dateBirth("1993-04-16")
                        .mobilePhoneNumber("010-4321-4321")
                        .build()
                ))
                .thenReturn(Patient.builder()
                        .id(2L)
                        .hospital(hospital)
                        .name("권혜원")
                        .genderCode("F")
                        .registrationNumber("202200001")
                        .dateBirth("1993-04-16")
                        .mobilePhoneNumber("010-4321-4321")
                        .build()
                );

        // when
        PatientUpdateResponse updatedPatient = (PatientUpdateResponse)patientService.updatePatient(2L, patientUpdateRequest);

        log.info("수정된 환자 => " + updatedPatient);

        // then
        assertEquals(updatedPatient.getId(), 2L);
        assertEquals(updatedPatient.getName(), "권혜원");
        assertEquals(updatedPatient.getGenderCode(), "F");
        assertEquals(updatedPatient.getRegistrationNumber(), "202200001");
        assertEquals(updatedPatient.getDateBirth(), "1993-04-16");
        assertEquals(updatedPatient.getMobilePhoneNumber(), "010-4321-4321");
    }

    @Test
    @DisplayName("환자 id 조회 테스트")
    public void getPatient() {
        Hospital hospital = Hospital.builder()
                .id(3L)
                .name("경기병원")
                .nursingInstitutionNumber("3")
                .directorName("박경기")
                .build();

        // given
        // 조회될 환자 mock 객체 생성
        Mockito.when(patientRepository.findById(2L))
                .thenReturn(Optional.ofNullable(Patient.builder()
                        .id(2L)
                        .hospital(hospital)
                        .name("오지웅")
                        .genderCode("M")
                        .registrationNumber("202200001")
                        .dateBirth("1994-04-12")
                        .mobilePhoneNumber("010-1234-1234")
                        .build()));


        // when
        PatientGetResponse searchedPatient = (PatientGetResponse)patientService.getPatient(2L);

        log.info("id 조회된 환자 => " + searchedPatient);

        // then
        assertEquals(searchedPatient.getId(), 2L);
        assertEquals(searchedPatient.getName(), "오지웅");
        assertEquals(searchedPatient.getGenderCode(), "M");
        assertEquals(searchedPatient.getRegistrationNumber(), "202200001");
        assertEquals(searchedPatient.getDateBirth(), "1994-04-12");
        assertEquals(searchedPatient.getMobilePhoneNumber(), "010-1234-1234");
    }

    @Test
    @DisplayName("전체 환자 조회 테스트")
    public void getAllPatient() {
        Hospital hospital = Hospital.builder()
                .id(3L)
                .name("경기병원")
                .nursingInstitutionNumber("3")
                .directorName("박경기")
                .build();

        List<Visit> visitList = new ArrayList<>();

        visitList.add(Visit.builder()
                            .id(2L)
                            .receptionDate(LocalDateTime.of(2022, 4, 20, 13, 20))
                            .visitStatusCode("1")
                            .build());

        visitList.add(Visit.builder()
                            .id(1L)
                            .receptionDate(LocalDateTime.of(2022, 4, 12, 00, 00))
                            .visitStatusCode("3")
                            .build());



        List<Patient> patientList = new ArrayList<>();

        patientList.add(Patient.builder()
                                .id(1L)
                                .hospital(hospital)
                                .name("오지웅")
                                .genderCode("M")
                                .registrationNumber("202200001")
                                .dateBirth("1994-04-12")
                                .mobilePhoneNumber("010-1234-1234")
                                .visitList(visitList)
                                .build());

        patientList.add(Patient.builder()
                                .id(2L)
                                .hospital(hospital)
                                .name("유재석")
                                .genderCode("M")
                                .registrationNumber("202200002")
                                .dateBirth("1954-06-21")
                                .mobilePhoneNumber("010-1345-1345")
                                .visitList(visitList)
                                .build());

        Pageable pageable =  PageRequest.of(1, 8);

        Page<Patient>  patients = new PageImpl(patientList, pageable, patientList.size());

        // given
        // 조회될 환자 mock 객체 생성
        Mockito.when(patientRepositoryImpl.findByAllSearchKeyword(PatientSearchKeyword.builder()
                        .name("")
                        .registrationNumber("")
                        .dateBirth("")
                        .build(), pageable))
                .thenReturn(patients);

        // when
        Page<PatientResponse> searchedAllPatient = patientService.getAllPatient(PatientSearchKeyword.builder()
                .name("")
                .registrationNumber("")
                .dateBirth("")
                .build(), pageable
        );

        PatientGetAllResponse firstPatient = (PatientGetAllResponse)searchedAllPatient.getContent().get(0);
        PatientGetAllResponse secondPatient = (PatientGetAllResponse)searchedAllPatient.getContent().get(1);

        log.info("전체 조회된 환자 => " + searchedAllPatient);

        // then
        assertEquals(firstPatient.getId(), 1L);
        assertEquals(firstPatient.getName(), "오지웅");
        assertEquals(firstPatient.getGenderCode(), "M");
        assertEquals(firstPatient.getRegistrationNumber(), "202200001");
        assertEquals(firstPatient.getDateBirth(), "1994-04-12");
        assertEquals(firstPatient.getMobilePhoneNumber(), "010-1234-1234");
        assertEquals(firstPatient.getRecentReceptionDate(), "2022-04-20");

        assertEquals(secondPatient.getId(), 2L);
        assertEquals(secondPatient.getName(), "유재석");
        assertEquals(secondPatient.getGenderCode(), "M");
        assertEquals(secondPatient.getRegistrationNumber(), "202200002");
        assertEquals(secondPatient.getDateBirth(), "1954-06-21");
        assertEquals(secondPatient.getMobilePhoneNumber(), "010-1345-1345");
        assertEquals(secondPatient.getRecentReceptionDate(), "2022-04-20");
    }
}
