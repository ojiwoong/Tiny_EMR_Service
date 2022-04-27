-- 코드그룹
INSERT INTO CODE_GROUP (code_group, name, description) VALUES ('genderCode', '성별코드', '성별을 표시');
INSERT INTO CODE_GROUP (code_group, name, description) VALUES ('visitStatusCode', '방문상태코드', '환자 방문의 상태(방문중, 종료, 취소)');
INSERT INTO CODE_GROUP (code_group, name, description) VALUES ('treatmentSubjectCode', '진료과목코드', '진료과목(내과, 안과 등');
INSERT INTO CODE_GROUP (code_group, name, description) VALUES ('treatmentTypeCode', '진료유형코드', '진료의 유형(약처방, 검사 등)');

-- 성별코드
INSERT INTO CODE (code_group, code, name) VALUES ('genderCode', 'M', '남');
INSERT INTO CODE (code_group, code, name) VALUES ('genderCode', 'F', '여');

-- 방문상태코드
INSERT INTO CODE (code_group, code, name) VALUES ('visitStatusCode', '1', '방문중');
INSERT INTO CODE (code_group, code, name) VALUES ('visitStatusCode', '2', '종료');
INSERT INTO CODE (code_group, code, name) VALUES ('visitStatusCode', '3', '취소');

-- 진료과목코드
INSERT INTO CODE (code_group, code, name) VALUES ('treatmentSubjectCode', '01', '내과');
INSERT INTO CODE (code_group, code, name) VALUES ('treatmentSubjectCode', '02', '안과');

-- 진료유형코드
INSERT INTO CODE (code_group, code, name) VALUES ('treatmentTypeCode', 'D', '약처방');
INSERT INTO CODE (code_group, code, name) VALUES ('treatmentTypeCode', 'T', '검사');

-- 병원
INSERT INTO HOSPITAL (name, nursing_institution_number, director_name) VALUES ('서울병원', '1', '김서울');
INSERT INTO HOSPITAL (name, nursing_institution_number, director_name) VALUES ('인천병원', '2', '이인천');
INSERT INTO HOSPITAL (name, nursing_institution_number, director_name) VALUES ('경기병원', '3', '박경기');
INSERT INTO HOSPITAL (name, nursing_institution_number, director_name) VALUES ('강원병원', '4', '나강원');

-- 환자
INSERT INTO PATIENT (date_birth, gender_code, mobile_phone_number, name, registration_number, hospital_id) VALUES ('1994-04-12', 'M', '010-1234-1234', '오지웅', '2022000001', 2);
INSERT INTO PATIENT (date_birth, gender_code, mobile_phone_number, name, registration_number, hospital_id) VALUES ('1954-06-21', 'M', '010-1345-1345', '유재석', '2022000001', 3);
INSERT INTO PATIENT (date_birth, gender_code, mobile_phone_number, name, registration_number, hospital_id) VALUES ('1964-04-01', 'M', '010-3453-2343', '조세호', '2022000001', 1);
INSERT INTO PATIENT (date_birth, gender_code, mobile_phone_number, name, registration_number, hospital_id) VALUES ('1974-02-04', 'F', '010-5234-6234', '신봉선', '2022000002', 1);
INSERT INTO PATIENT (date_birth, gender_code, mobile_phone_number, name, registration_number, hospital_id) VALUES ('1944-03-19', 'M', '010-4325-4324', '정준하', '2022000003', 1);
INSERT INTO PATIENT (date_birth, gender_code, mobile_phone_number, name, registration_number, hospital_id) VALUES ('1954-07-16', 'M', '010-4324-4324', '하동훈', '2022000004', 1);

-- 환자 방문
INSERT INTO VISIT (RECEPTION_DATE, VISIT_STATUS_CODE, HOSPITAL_ID, PATIENT_ID) VALUES ({ts '2022-04-24 10:37:22.69'}, 3, 2, 1);
INSERT INTO VISIT (RECEPTION_DATE, VISIT_STATUS_CODE, HOSPITAL_ID, PATIENT_ID) VALUES ({ts '2022-04-26 11:47:32.59'}, 2, 2, 1);
INSERT INTO VISIT (RECEPTION_DATE, VISIT_STATUS_CODE, HOSPITAL_ID, PATIENT_ID) VALUES ({ts '2022-04-25 12:57:42.49'}, 3, 3, 2);
INSERT INTO VISIT (RECEPTION_DATE, VISIT_STATUS_CODE, HOSPITAL_ID, PATIENT_ID) VALUES ({ts '2022-04-26 14:07:52.39'}, 1, 3, 2);


