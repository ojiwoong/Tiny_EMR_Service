package com.hdjunction.tinyERMService.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PatientSearchKeyword {
    private String name;
    private String registrationNumber;
    private String dateBirth;

    @Builder
    public PatientSearchKeyword(String name, String registrationNumber, String dateBirth) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.dateBirth = dateBirth;
    }
}
