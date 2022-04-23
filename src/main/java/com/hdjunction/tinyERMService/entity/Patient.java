package com.hdjunction.tinyERMService.entity;

import javax.persistence.*;

@Entity
@Table(name = "patient")
public class Patient {

    // 환자 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long id;

    // 병원정보
    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    // 환자명
    @Column(nullable = false, length = 45)
    private String name;

    // 환자등록번호
    @Column(nullable = false, length = 13)
    private String registrationNumber;

    // 성별코드
    @Column(nullable = false, length = 13)
    private String genderCode;

    // 생년월일
    @Column(length = 10)
    private String dateBirth;

    // 휴대전화번호
    @Column(length = 20)
    private String mobilePhoneNumber;
}
