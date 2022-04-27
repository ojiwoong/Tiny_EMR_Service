package com.hdjunction.tinyERMService.repository;

import com.hdjunction.tinyERMService.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    
}
