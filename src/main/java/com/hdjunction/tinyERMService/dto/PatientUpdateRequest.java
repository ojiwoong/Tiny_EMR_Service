package com.hdjunction.tinyERMService.dto;

import com.hdjunction.tinyERMService.entity.Hospital;
import com.hdjunction.tinyERMService.entity.Patient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
public class PatientUpdateRequest {
    // 환자명
    private String name;

    // 성별코드
    private String genderCode;

    // 생년월일
    private String dateBirth;

    // 휴대전화번호
    private String mobilePhoneNumber;

    @Builder
    public PatientUpdateRequest(String name, String genderCode, String dateBirth, String mobilePhoneNumber) {
        this.name = name;
        this.genderCode = genderCode;
        this.dateBirth = dateBirth;
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public Patient toEntity() {
        return Patient.builder()
                .name(name)
                .genderCode(genderCode)
                .dateBirth(dateBirth)
                .mobilePhoneNumber(mobilePhoneNumber)
                .build();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mobilePhoneNumber, genderCode, dateBirth);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof PatientUpdateRequest))
            return false;

        PatientUpdateRequest patientCreateRequest = (PatientUpdateRequest) obj;
        return name.equals(patientCreateRequest.name) &&
                mobilePhoneNumber.equals(patientCreateRequest.mobilePhoneNumber) &&
                genderCode.equals(patientCreateRequest.genderCode) &&
                dateBirth.equals(patientCreateRequest.dateBirth);

    }
}
