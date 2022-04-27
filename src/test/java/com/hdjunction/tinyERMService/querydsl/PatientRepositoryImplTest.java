package com.hdjunction.tinyERMService.querydsl;

import com.hdjunction.tinyERMService.entity.Patient;
import com.hdjunction.tinyERMService.service.PatientServiceImpl;
import com.hdjunction.tinyERMService.service.PatientServiceImplTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PatientRepositoryImplTest {
    Logger log = (Logger) LoggerFactory.getLogger(PatientServiceImplTest.class);

    @Autowired
    PatientRepositoryImpl patientRepository;

    @Autowired
    PatientServiceImpl patientService;

    @Test
    @DisplayName("환자 동적 환자명 검색")
    void patientDynamicSearchByName() {
        PatientSearchKeyword patientSearchKeyword = PatientSearchKeyword.builder()
                .name("오지웅")
                .registrationNumber("")
                .dateBirth("")
                .build();

        List<Patient> patients = patientRepository.findByAllSearchKeyword(patientSearchKeyword);
        
        assertEquals(patients.size(), 1);
        assertEquals(patients.get(0).getName(), "오지웅");
        assertEquals(patients.get(0).getRegistrationNumber(), "2022000001");
        assertEquals(patients.get(0).getGenderCode(), "M");
        assertEquals(patients.get(0).getDateBirth(), "1994-04-12");
        assertEquals(patients.get(0).getMobilePhoneNumber(), "010-1234-1234");
    }

    @Test
    @DisplayName("환자 동적 환자등록번호 검색")
    void patientDynamicSearchByRegistrationNumber() {
        PatientSearchKeyword patientSearchKeyword = PatientSearchKeyword.builder()
                .name("")
                .registrationNumber("2022000001")
                .dateBirth("")
                .build();

        List<Patient> patients = patientRepository.findByAllSearchKeyword(patientSearchKeyword);

        assertEquals(patients.size(), 3);
    }


    @Test
    @DisplayName("환자 동적 생년월일 검색 ")
    void patientDynamicSearchDateBirth() {
        PatientSearchKeyword patientSearchKeyword = PatientSearchKeyword.builder()
                .name("")
                .registrationNumber("")
                .dateBirth("1964-04-01")
                .build();

        List<Patient> patients = patientRepository.findByAllSearchKeyword(patientSearchKeyword);

        assertEquals(patients.size(), 1);
        assertEquals(patients.get(0).getName(), "조세호");
        assertEquals(patients.get(0).getRegistrationNumber(), "2022000001");
        assertEquals(patients.get(0).getGenderCode(), "M");
        assertEquals(patients.get(0).getDateBirth(), "1964-04-01");
        assertEquals(patients.get(0).getMobilePhoneNumber(), "010-3453-2343");
    }


    @Test
    @DisplayName("환자 동적 전체 검색 ")
    void patientDynamicSearchAll() {
        PatientSearchKeyword patientSearchKeyword = PatientSearchKeyword.builder()
                .name("")
                .registrationNumber("")
                .dateBirth("")
                .build();

        List<Patient> patients = patientRepository.findByAllSearchKeyword(patientSearchKeyword);

        assertEquals(patients.size(), 6);
    }

    @Test
    @DisplayName("환자 동적 전체 검색 페이징 처리")
    void patientDynamicSearchAllUsingPaging() {
        PatientSearchKeyword patientSearchKeyword = PatientSearchKeyword.builder()
                .name("")
                .registrationNumber("")
                .dateBirth("")
                .build();

        Pageable pageable =  PageRequest.of(1, 3);

        Page<Patient> patients = patientRepository.findByAllSearchKeyword(patientSearchKeyword,pageable);

        assertEquals(patients.getContent().size(), 3);
    }
    
}
