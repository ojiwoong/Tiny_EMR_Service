package com.hdjunction.tinyERMService.entity;

import javax.persistence.*;

@Entity
@Table(name = "hospital")
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
}
