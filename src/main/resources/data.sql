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

