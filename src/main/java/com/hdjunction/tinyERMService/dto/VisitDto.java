package com.hdjunction.tinyERMService.dto;

import com.hdjunction.tinyERMService.entity.Hospital;
import com.hdjunction.tinyERMService.entity.Visit;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class VisitDto {
    private Long id;
    private LocalDateTime receptionDate;
    private String visitStatusCode;

    public VisitDto(Visit visit) {
        this.id = visit.getId();
        this.receptionDate = visit.getReceptionDate();
        this.visitStatusCode = visit.getVisitStatusCode();
    }

    @Builder
    public VisitDto(Long id, LocalDateTime receptionDate, String visitStatusCode) {
        this.id = id;
        this.receptionDate = receptionDate;
        this.visitStatusCode = visitStatusCode;
    }
}
