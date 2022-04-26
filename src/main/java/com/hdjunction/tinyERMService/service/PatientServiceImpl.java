package com.hdjunction.tinyERMService.service;

import com.hdjunction.tinyERMService.dto.PatientCreateRequest;
import com.hdjunction.tinyERMService.dto.PatientUpdateRequest;
import com.hdjunction.tinyERMService.entity.Hospital;
import com.hdjunction.tinyERMService.entity.Patient;
import com.hdjunction.tinyERMService.repository.HospitalRepository;
import com.hdjunction.tinyERMService.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService{
    PatientRepository patientRepository;
    HospitalRepository hospitalRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, HospitalRepository hospitalRepository) {
        this.patientRepository = patientRepository;
        this.hospitalRepository = hospitalRepository;
    }

    // 환자 등록
    @Override
    @Transactional
    public Patient createPatient(PatientCreateRequest patientCreateRequest) {

        String createdRegistrationNumber = createRegistrationNumber(patientCreateRequest.getHospitalId());

        Hospital hospital = hospitalRepository.findById(patientCreateRequest.getHospitalId()).orElse(null);

        Patient patient = null;

        if(hospital != null){
            patient = patientRepository.save(patientCreateRequest.toEntity(hospital, createdRegistrationNumber));
        }

        return patient;
    }

    // 환자 수정
    @Override
    public Patient updatePatient(Long patientId, PatientUpdateRequest patientUpdateRequest) {
        // 수정할 환자 조회
        Patient patient = patientRepository.findById(patientId).orElse(null);

        if(patient != null){
            // 환자 데이터 수정 (이름, 성별, 휴대폰번호, 생년월일)
            patient.setDateBirth(patientUpdateRequest.getDateBirth());
            patient.setGenderCode(patientUpdateRequest.getGenderCode());
            patient.setName(patientUpdateRequest.getName());
            patient.setMobilePhoneNumber(patientUpdateRequest.getMobilePhoneNumber());

            patient = patientRepository.save(patient);
        }

        return patient;
    }

    // 환자 등록번호 생성
    @Override
    public String createRegistrationNumber(Long hospitalId) {
        StringBuilder sb = new StringBuilder();
        int newNumber = 1;

        String maxRegistrationNumber = patientRepository.getMaxRegistrationNumber(hospitalId);

        int thisYear = LocalDate.now().getYear();

        // 환자 등록번호 데이터가 없거나 정상적인 데이터가 아닐경우 (데이터 크기 9로 고정 <연도 4자리 + seq 5자리>)
        // 올해의 신규등록 번호로 리턴 ==> ex) 2022년의 경우 202200001로 리턴
        if (maxRegistrationNumber == null || maxRegistrationNumber.length() != 9){
            sb.append(thisYear);
            sb.append(String.format("%05d", newNumber));

            return sb.toString();
        }

        // 연도(year) / 환자등록번호 문자열 자르기, 다음등록번호
        int year = Integer.parseInt(maxRegistrationNumber.substring(0, 4));
        int nextNumber = Integer.parseInt(maxRegistrationNumber.substring(4, 9)) + 1;

        // 연도가 다른경우 => 올해 연도 + 신규환자등록번호
        if(year != thisYear){
            sb.append(thisYear);
            sb.append(String.format("%05d", newNumber));

            return sb.toString();
        }

        sb.append(year);
        sb.append(String.format("%05d", nextNumber));


        return sb.toString();
    }
}
