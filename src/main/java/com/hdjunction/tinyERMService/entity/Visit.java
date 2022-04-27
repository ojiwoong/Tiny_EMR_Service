package com.hdjunction.tinyERMService.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visit")
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Visit {
    // 환자방문 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private Long id;

    // 병원
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    // 환자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // 접수일시
    @Column(nullable = false)
    private LocalDateTime receptionDate;

    // 방문상태코드
    @Column(nullable = false, length = 10)
    private String visitStatusCode;

    @Builder
    public Visit(Long id, Hospital hospital, Patient patient, LocalDateTime receptionDate, String visitStatusCode) {
        this.id = id;
        this.hospital = hospital;
        this.patient = patient;
        this.receptionDate = receptionDate;
        this.visitStatusCode = visitStatusCode;
    }
}
