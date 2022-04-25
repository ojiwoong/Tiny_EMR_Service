package com.hdjunction.tinyERMService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdjunction.tinyERMService.dto.PatientCreateRequest;
import com.hdjunction.tinyERMService.entity.Hospital;
import com.hdjunction.tinyERMService.entity.Patient;
import com.hdjunction.tinyERMService.repository.HospitalRepository;
import com.hdjunction.tinyERMService.repository.PatientRepository;
import com.hdjunction.tinyERMService.service.PatientServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // PatientController 에서 사용되는 Bean 객체에 대해 Mock 형태의 객체를 생성
    @MockBean
    PatientServiceImpl patientService;

    // POST: http://localhost:8080/patient
    @Test
    @DisplayName("환자 등록 테스트")
    void createPatient() throws Exception {
        Hospital hospital = Hospital.builder()
                                    .id(1L)
                                    .name("서울 병원")
                                    .nursingInstitutionNumber("1")
                                    .directorName("김서울")
                                    .build();


        // given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(patientService.createPatient(new PatientCreateRequest(1L, "오지웅", "M",
                                                            "1994-04-12", "010-1234-1234")))
                .willReturn(
                new Patient(1L, hospital, "오지웅", "202200001","M", "1994-04-12", "010-1234-1234")
        );

        PatientCreateRequest patientCreateRequest = PatientCreateRequest.builder()
                                .hospitalId(1L)
                                .name("오지웅")
                                .genderCode("M")
                                .dateBirth("1994-04-12")
                                .mobilePhoneNumber("010-1234-1234")
                                .build();


        String content = new ObjectMapper().writeValueAsString(patientCreateRequest);


        String expectDataById = "$..data[?(@.id == '%s')]";
        String expectDataByName = "$..data[?(@.name == '%s')]";
        String expectDataByRegistrationNumber = "$..data[?(@.registrationNumber == '%s')]";
        String expectDataByGenderCode = "$..data[?(@.genderCode == '%s')]";
        String expectDataByDateBirth = "$..data[?(@.dateBirth == '%s')]";
        String expectDataByMobilePhoneNumber = "$..data[?(@.mobilePhoneNumber == '%s')]";

        String expectHospitalById = "$..data.hospital[?(@.id == '%s')]";
        String expectHospitalByName = "$..data.hospital[?(@.name == '%s')]";
        String expectHospitalByNursingInstitutionNumber = "$..data.hospital[?(@.nursingInstitutionNumber == '%s')]";
        String expectHospitalByDirectorName = "$..data.hospital[?(@.directorName == '%s')]";



        // andExpect : 기대하는 값이 나왔는지 체크
        mockMvc.perform(
                post("/patient")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(expectDataById, 1).exists())
                .andExpect(jsonPath(expectDataByName, "오지웅").exists())
                .andExpect(jsonPath(expectDataByRegistrationNumber, "202200001").exists())
                .andExpect(jsonPath(expectDataByGenderCode, "M").exists())
                .andExpect(jsonPath(expectDataByDateBirth, "1994-04-12").exists())
                .andExpect(jsonPath(expectDataByMobilePhoneNumber, "010-1234-1234").exists())

                .andExpect(jsonPath(expectHospitalById, 1).exists())
                .andExpect(jsonPath(expectHospitalByName, "서울 병원").exists())
                .andExpect(jsonPath(expectHospitalByNursingInstitutionNumber, 1).exists())
                .andExpect(jsonPath(expectHospitalByDirectorName, "김서울").exists())
                .andDo(print());
    }
}
