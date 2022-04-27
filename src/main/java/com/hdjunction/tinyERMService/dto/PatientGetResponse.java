package com.hdjunction.tinyERMService.dto;

import com.hdjunction.tinyERMService.entity.Hospital;
import com.hdjunction.tinyERMService.entity.Visit;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PatientGetResponse {
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
}
