package com.hdjunction.tinyERMService.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class PatientCreateResponse implements PatientResponse {
    private Long id;
    private String name;
    private String registrationNumber;
    private String genderCode;
    private String dateBirth;
    private String mobilePhoneNumber;

    @Builder
    public PatientCreateResponse(Long id, String name, String registrationNumber, String genderCode, String dateBirth, String mobilePhoneNumber) {
        this.id = id;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.genderCode = genderCode;
        this.dateBirth = dateBirth;
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mobilePhoneNumber, genderCode, dateBirth);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof PatientCreateResponse))
            return false;

        PatientCreateResponse patientCreateResponse = (PatientCreateResponse) obj;
        return id.equals(patientCreateResponse.id) &&
                name.equals(patientCreateResponse.name) &&
                mobilePhoneNumber.equals(patientCreateResponse.mobilePhoneNumber) &&
                genderCode.equals(patientCreateResponse.genderCode) &&
                dateBirth.equals(patientCreateResponse.dateBirth);

    }
}
