package com.hdjunction.tinyERMService.controller;

import com.hdjunction.tinyERMService.dto.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class PatientController {

    // 환자 생성
    @PostMapping("/patient")
    public ResponseEntity<Message> createPatient(){

        Message message = new Message();
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    // 환자 수정
   @PutMapping("/patient/{id}")
    public ResponseEntity<Message> updatePatient(@PathVariable(name = "id") Long id){

        Message message = new Message();
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    // 환자 삭제
    @DeleteMapping("/patient/{id}")
    public ResponseEntity<Message> deletePatient(@PathVariable(name = "id") Long id){

        Message message = new Message();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
    }

    // 전체 환자 조회
    @GetMapping("/patient")
    public ResponseEntity<Message> getAllPatient(@PathVariable(name = "id") Long id){

        Message message = new Message();
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    // 환자 id 조회
    @GetMapping("/patient/{id}")
    public ResponseEntity<Message> getPatient(@PathVariable(name = "id") Long id){

        Message message = new Message();
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
