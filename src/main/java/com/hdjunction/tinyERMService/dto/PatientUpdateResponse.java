package com.hdjunction.tinyERMService.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class PatientUpdateResponse implements PatientResponse{
    private Long id;
    private String name;
    private String registrationNumber;
    private String genderCode;
    private String dateBirth;
    private String mobilePhoneNumber;

    @Builder
    public PatientUpdateResponse(Long id, String name, String registrationNumber, String genderCode, String dateBirth, String mobilePhoneNumber) {
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
        if(!(obj instanceof PatientUpdateResponse))
            return false;

        PatientUpdateResponse patientUpdateResponse = (PatientUpdateResponse) obj;
        return  id == id &&
                name.equals(patientUpdateResponse.name) &&
                mobilePhoneNumber.equals(patientUpdateResponse.mobilePhoneNumber) &&
                genderCode.equals(patientUpdateResponse.genderCode) &&
                dateBirth.equals(patientUpdateResponse.dateBirth);

    }
}
