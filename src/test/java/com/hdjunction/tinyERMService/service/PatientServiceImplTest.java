package com.hdjunction.tinyERMService.service;

import com.hdjunction.tinyERMService.dto.PatientCreateRequest;
import com.hdjunction.tinyERMService.entity.Hospital;
import com.hdjunction.tinyERMService.entity.Patient;
import com.hdjunction.tinyERMService.repository.HospitalRepository;
import com.hdjunction.tinyERMService.repository.PatientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@Import({PatientServiceImpl.class, PatientRepository.class, HospitalRepository.class})
public class PatientServiceImplTest {
    Logger log = (Logger) LoggerFactory.getLogger(PatientServiceImplTest.class);

    @MockBean
    PatientRepository patientRepository;

    @MockBean
    HospitalRepository hospitalRepository;

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
        Patient cratedPatient = patientService.createPatient(patientCreateRequest);

        log.info("생성된 환자 => " + cratedPatient);

        // then
        assertEquals(cratedPatient.getId(), 1L);
        assertEquals(cratedPatient.getName(), "오지웅");
        assertEquals(cratedPatient.getGenderCode(), "M");
        assertEquals(cratedPatient.getRegistrationNumber(), "202200001");
        assertEquals(cratedPatient.getDateBirth(), "1994-04-12");
        assertEquals(cratedPatient.getMobilePhoneNumber(), "010-1234-1234");
    }
}
