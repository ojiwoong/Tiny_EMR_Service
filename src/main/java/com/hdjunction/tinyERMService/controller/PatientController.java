package com.hdjunction.tinyERMService.controller;

import com.hdjunction.tinyERMService.dto.*;
import com.hdjunction.tinyERMService.entity.Patient;
import com.hdjunction.tinyERMService.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        PatientResponse createdPatient = patientService.createPatient(patientCreateRequest);

        Message message = new Message();

        if(createdPatient != null){
            message.setStatus(StatusEnum.OK);
            message.setData(createdPatient);
            message.setMessage("환자 등록 성공");

            return ResponseEntity.status(HttpStatus.OK).body(message);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    // 환자 수정
   @PutMapping("/patient/{id}")
    public ResponseEntity<Message> updatePatient(@PathVariable(name = "id") Long id, @RequestBody PatientUpdateRequest updateRequest){

        // 환자 수정 서비스 호출
       PatientResponse updatedPatient = patientService.updatePatient(id, updateRequest);

       Message message = new Message();

       if(updatedPatient != null){
           message.setStatus(StatusEnum.OK);
           message.setData(updatedPatient);
           message.setMessage("환자 수정 성공");

           return ResponseEntity.status(HttpStatus.OK).body(message);
       }

       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    // 환자 삭제
    @DeleteMapping("/patient/{id}")
    public ResponseEntity<Message> deletePatient(@PathVariable(name = "id") Long id){

        Message message = new Message();

        try {
            // 환자 삭제 서비스 호출
            patientService.deletePatient(id);

            message.setMessage(id + "번 환자 삭제 완료");
            message.setStatus(StatusEnum.OK);

            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        // 미등록 환자 삭제 시
        catch (EmptyResultDataAccessException ex){
            message.setMessage("해당 id로 등록된 환자가 존재하지 않습니다.");
            message.setStatus(StatusEnum.NO_CONTENT);

            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        // 그외 예외 발생 시
        catch (Exception ex) {
            message.setMessage(ex.getMessage());
            message.setStatus(StatusEnum.INTERNAL_SERER_ERROR);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    // 환자 id 조회
    @GetMapping("/patient/{id}")
    public ResponseEntity<Message> getPatient(@PathVariable(name = "id") Long id){

        // 환자 조회 서비스 호출
        PatientResponse searchedPatient = patientService.getPatient(id);

        Message message = new Message();

        if(searchedPatient != null){
            message.setStatus(StatusEnum.OK);
            message.setData(searchedPatient);
            message.setMessage(id + "번 환자 조회 성공");

            return ResponseEntity.status(HttpStatus.OK).body(message);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    // 전체 환자 조회
    @GetMapping("/patient")
    public ResponseEntity<Message> getAllPatient(@RequestBody PatientSearchKeyword patientSearchKeyword){

        // 전체 환자 조회 서비스 호출
        List<PatientResponse> searchedPatientList = patientService.getAllPatient(patientSearchKeyword);

        Message message = new Message();

        if(searchedPatientList != null){
            message.setStatus(StatusEnum.OK);
            message.setData(searchedPatientList);
            message.setMessage("전체 환자 조회 성공");

            return ResponseEntity.status(HttpStatus.OK).body(message);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }
}
