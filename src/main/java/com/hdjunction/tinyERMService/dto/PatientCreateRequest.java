package com.hdjunction.tinyERMService.dto;

import com.hdjunction.tinyERMService.entity.Hospital;
import com.hdjunction.tinyERMService.entity.Patient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
public class PatientCreateRequest {
    // 병원 ID
    private Long hospitalId;

    // 환자명
    private String name;

    // 성별코드
    private String genderCode;

    // 생년월일
    private String dateBirth;

    // 휴대전화번호
    private String mobilePhoneNumber;

    @Builder
    public PatientCreateRequest(Long hospitalId, String name, String genderCode, String dateBirth, String mobilePhoneNumber) {
        this.hospitalId = hospitalId;
        this.name = name;
        this.genderCode = genderCode;
        this.dateBirth = dateBirth;
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public Patient toEntity(Hospital hospital, String createdRegistrationNumber) {
        return Patient.builder()
                .hospital(hospital)
                .name(name)
                .registrationNumber(createdRegistrationNumber)
                .genderCode(genderCode)
                .dateBirth(dateBirth)
                .mobilePhoneNumber(mobilePhoneNumber)
                .build();
    }

    @Override
    public int hashCode() {
        return Objects.hash(hospitalId, name, mobilePhoneNumber, genderCode, dateBirth);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof PatientCreateRequest))
            return false;

        PatientCreateRequest patientCreateRequest = (PatientCreateRequest) obj;
        return hospitalId == patientCreateRequest.hospitalId &&
                name.equals(patientCreateRequest.name) &&
                mobilePhoneNumber.equals(patientCreateRequest.mobilePhoneNumber) &&
                genderCode.equals(patientCreateRequest.genderCode) &&
                dateBirth.equals(patientCreateRequest.dateBirth);

    }
}
