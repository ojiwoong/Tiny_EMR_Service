package com.hdjunction.tinyERMService.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hospital")
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Hospital {

    // 병원 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private Long id;

    // 병원명
    @Column(nullable = false, length = 45)
    private String name;

    // 요양기관번호
    @Column(nullable = false, length = 45)
    private String nursingInstitutionNumber;

    // 병원장명
    @Column(nullable = false, length = 45)
    private String directorName;

    @Builder
    public Hospital(Long id, String name, String nursingInstitutionNumber, String directorName) {
        this.id = id;
        this.name = name;
        this.nursingInstitutionNumber = nursingInstitutionNumber;
        this.directorName = directorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        Hospital hospital = (Hospital) o;
        return Objects.equals(id, hospital.id);
    }

    @Override
    public int hashCode() {
        return id.intValue();
    }
}
