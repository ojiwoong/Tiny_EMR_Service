package com.hdjunction.tinyERMService.dto;

import com.hdjunction.tinyERMService.entity.Hospital;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class PatientGetAllResponse implements PatientResponse{
    private Long id;

    private String name;

    private String registrationNumber;

    private String genderCode;

    private String dateBirth;

    private String mobilePhoneNumber;

    private String recentReceptionDate;

    @Builder
    public PatientGetAllResponse(Long id, String name, String registrationNumber, String genderCode, String dateBirth, String mobilePhoneNumber, String recentReceptionDate) {
        this.id = id;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.genderCode = genderCode;
        this.dateBirth = dateBirth;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.recentReceptionDate = recentReceptionDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, registrationNumber, mobilePhoneNumber, genderCode, dateBirth, recentReceptionDate);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof PatientGetAllResponse))
            return false;

        PatientGetAllResponse patientGetAllResponse = (PatientGetAllResponse) obj;
        return
                id == id &&
                name.equals(patientGetAllResponse.name) &&
                registrationNumber.equals(patientGetAllResponse.registrationNumber) &&
                        dateBirth.equals(patientGetAllResponse.dateBirth) &&
                genderCode.equals(patientGetAllResponse.genderCode) &&
                mobilePhoneNumber.equals(patientGetAllResponse.mobilePhoneNumber) &&
                recentReceptionDate.equals(patientGetAllResponse.recentReceptionDate)
                ;
    }
}
