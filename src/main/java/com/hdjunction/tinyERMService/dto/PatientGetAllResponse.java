package com.hdjunction.tinyERMService.dto;

import com.hdjunction.tinyERMService.entity.Hospital;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PatientGetAllResponse {
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
}
