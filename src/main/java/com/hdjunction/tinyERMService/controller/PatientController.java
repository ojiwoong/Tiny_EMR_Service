package com.hdjunction.tinyERMService.controller;

import com.hdjunction.tinyERMService.dto.Message;
import com.hdjunction.tinyERMService.dto.PatientCreateRequest;
import com.hdjunction.tinyERMService.dto.StatusEnum;
import com.hdjunction.tinyERMService.entity.Patient;
import com.hdjunction.tinyERMService.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class PatientController {

    PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // 환자 등록
    @PostMapping("/patient")
    public ResponseEntity<Message> createPatient(@RequestBody PatientCreateRequest patientCreateRequest){

        // 환자 등록 서비스 호출
        Patient patient = patientService.createPatient(patientCreateRequest);

        Message message = new Message();

        if(patient != null){
            message.setStatus(StatusEnum.OK);
            message.setData(patient);
            message.setMessage("환자 등록 성공");

            return ResponseEntity.status(HttpStatus.OK).body(message);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
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
