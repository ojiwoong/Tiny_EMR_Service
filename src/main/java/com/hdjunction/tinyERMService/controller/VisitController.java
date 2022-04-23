package com.hdjunction.tinyERMService.controller;

import com.hdjunction.tinyERMService.dto.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class VisitController {

    // 환자방문 생성
    @PostMapping("/visit")
    public ResponseEntity<Message> createVisit(){

        Message message = new Message();
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    // 환자방문 수정
    @PutMapping("/visit/{id}")
    public ResponseEntity<Message> updateVisit(){

        Message message = new Message();
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    // 환자방문 삭제
    @DeleteMapping("/visit/{id}")
    public ResponseEntity<Message> deleteVisit(@PathVariable(name = "id") Long id){

        Message message = new Message();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
    }

    // 전체 환자방문 조회
    @GetMapping("/visit")
    public ResponseEntity<Message> getAllVisit(@PathVariable(name = "id") Long id){

        Message message = new Message();
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    // 환자방문 id 조회
    @GetMapping("/visit/{id}")
    public ResponseEntity<Message> getVisit(@PathVariable(name = "id") Long id){

        Message message = new Message();
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
