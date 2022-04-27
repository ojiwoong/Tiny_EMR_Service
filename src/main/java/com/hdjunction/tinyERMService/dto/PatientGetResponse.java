package com.hdjunction.tinyERMService.dto;

import com.hdjunction.tinyERMService.entity.Hospital;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class PatientGetResponse implements PatientResponse{
    private Long id;

    private Hospital hospital;

    private String name;

    private String registrationNumber;

    private String genderCode;

    private String dateBirth;

    private String mobilePhoneNumber;

    private List<VisitDto> visit = new ArrayList<>();

    @Builder
    public PatientGetResponse(Long id, Hospital hospital, String name, String registrationNumber, String genderCode, String dateBirth, String mobilePhoneNumber, List<VisitDto> visit) {
        this.id = id;
        this.hospital = hospital;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.genderCode = genderCode;
        this.dateBirth = dateBirth;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.visit = visit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hospital, visit, name, mobilePhoneNumber, genderCode, dateBirth);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof PatientGetResponse))
            return false;

        PatientGetResponse patientGetResponse = (PatientGetResponse) obj;
        return  id == id &&
                hospital == hospital &&
                visit == visit &&
                name.equals(patientGetResponse.name) &&
                mobilePhoneNumber.equals(patientGetResponse.mobilePhoneNumber) &&
                genderCode.equals(patientGetResponse.genderCode) &&
                dateBirth.equals(patientGetResponse.dateBirth) &&
                registrationNumber.equals(patientGetResponse.registrationNumber);

    }
}
