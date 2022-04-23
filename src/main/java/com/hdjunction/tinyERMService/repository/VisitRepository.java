package com.hdjunction.tinyERMService.repository;

import com.hdjunction.tinyERMService.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
