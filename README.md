## tiny_hospital_service

### 프로젝트 환경
- Language : Java 11.0.10
- Build : gradle
- FrameWork : Spring Boot 2.6.7
- DB : H2 Embedded
- ORM : JPA

### 라이브러리
- Lombok
- QueryDSL

### 커밋 컨밴션 정의

```bash
※ 커밋 종류 = 추가, 수정, 삭제 등...

--- 제목 ---
[커밋 종류]: [작업내용을 50자 이내 요약]
--- 본문 ---
[커밋 내용]
```

```bash
[커밋 종류]
# feat     : 기능 (새로운 기능)
# fix      : 버그 (버그 수정)
# refactor : 리팩토링
# docs     : 문서 (문서 추가, 수정, 삭제)
# test     : 테스트 (테스트 코드 추가, 수정, 삭제: 비즈니스 로직에 변경 없음)
# chore    : 기타 변경사항 (빌드 스크립트 수정 등)
```




### 파일 구조
```bash
├── src/
│    ├── main/
│    │   └── java/
│    │          ├── controller/
│    │          │     ├── PatientController.java
│    │          │     └── VisitController.java
│    │          ├── dto/
│    │          │     ├── CrudEnum.java
│    │          │     ├── Message.java
│    │          │     ├── PatientCreateRequest.java
│    │          │     ├── PatientCreateResponse.java
│    │          │     ├── PatientGetAllResponse.java
│    │          │     ├── PatientGetResponse.java
│    │          │     ├── PatientResponse.java
│    │          │     ├── PatientUpdateRequest.java
│    │          │     ├── PatientUpdateResponse.java
│    │          │     ├── StatusEnum.java
│    │          │     └── VisitDto.java
│    │          ├── entity/
│    │          │     ├── Code.java
│    │          │     ├── CodeGroup.java
│    │          │     ├── Hospital.java
│    │          │     ├── Patient.java
│    │          │     └── Visit.java
│    │          ├── querydsl/
│    │          │     ├── PatientRepositoryCustom.java
│    │          │     ├── PatientRepositoryImpl.java
│    │          │     ├── PatientSearchKeyword.java
│    │          │     └── QuerydslConfig.java
│    │          ├── repository/
│    │          │     ├── HospitalRepository.java
│    │          │     ├── PatientRepository.java
│    │          │     └── VisitRepository.java
│    │          ├── service/
│    │          │     ├── PatientService.java
│    │          │     ├── PatientServiceImpl.java
│    │          │     ├── VisitService.java
│    │          │     └── VisitServiceImpl.java
│    │          └── TinyErmServiceApplication.java
│    │
│    └── test
│        └── java/
│             ├── controller/
│             │    └── PatientControllerTest.java
│             ├── querydsl/
│             │    └── PatientRepositoryImplTest.java
│             ├── service/
│             │    └── PatientServiceImplTest.java
│             └── TinyErmServiceApplicationTests.java
│     
├── README.md
└── build.gradle
``` 

### 브랜치 구조
```bash
├── master: Release(배포)출시 할 수 있는 브랜치
├── develope: 다음 출시 버전을 개발하는 브랜치
└── feature/ : 기능을 개발하는 브랜치 (Local)
     └── patient-CRUD: 환자 CRUD 기능 구현 브랜치
     └── apply-querydsl: 환자 내역 조회 querydsl 기능 구현 브랜치
     └── apply-paging: 환자 내역 조회 페이징 기능 구현 브랜치
``` 

### RESTful API 명세서 정의

### `POST`  /p**atient**

- 환자 등록

  Request (RequestBody)

    ```
    {
      "hospitalId": 2,
      "name":"오지웅",
      "genderCode":"M",
      "dateBirth":"1994-04-12",
      "mobilePhoneNumber":"010-1234-1234"
    }
    ```

  Response `201(Created)`

    ```
    {
      "status": "OK",
      "message": "환자 등록 성공",
      "data": {
          "id": 7,
          "name": "오지웅",
          "registrationNumber": "202200001",
          "genderCode": "M",
          "dateBirth": "1994-04-12",
          "mobilePhoneNumber": "010-1234-1234"
      }
    }
    ```


#### `PUT` /p**atient/:id**

- 환자 수정

  Request (RequestBody)

    ```
    {
      "name":"오지웅",
      "genderCode":"M",
      "dateBirth":"1994-04-14",
      "mobilePhoneNumber":"010-1234-1234"
    }
    ```

  Response `200(OK)`

    ```
    {
      "status": "OK",
      "message": "환자 수정 성공",
      "data": {
          "id": 2,
          "name": "오지웅",
          "registrationNumber": "2022000001",
          "genderCode": "M",
          "dateBirth": "1994-04-14",
          "mobilePhoneNumber": "010-1234-1234"
      }
    }
    ```


#### `DELETE` /p**atient**/:**id**

- 환자 삭제

  Response `200(OK)`


#### `GET` /p**atient/**:**id**

- 환자 id 조회

  Response `200(OK)`

    ```
    {
      "status": "OK",
      "message": "1번 환자 조회 성공",
      "data": {
          "id": 1,
          "hospital": {
              "id": 2,
              "name": "인천병원",
              "nursingInstitutionNumber": "2",
              "directorName": "이인천"
          },
          "name": "오지웅",
          "registrationNumber": "2022000001",
          "genderCode": "M",
          "dateBirth": "1994-04-12",
          "mobilePhoneNumber": "010-1234-1234",
          "visit": [
              {
                  "id": 2,
                  "receptionDate": "2022-04-26T11:47:32.59",
                  "visitStatusCode": "2"
              },
              {
                  "id": 1,
                  "receptionDate": "2022-04-24T10:37:22.69",
                  "visitStatusCode": "3"
              }
          ]
    }
    ```


#### `GET` /p**atient**?size={한 페이지 컨텐츠 수}&page={조회할 페이지}

- 전체 환자 조회

  Request (RequestParam, RequestBody)

    ```
    {
      "registrationNumber": {검색할 환자등록번호},
      "name": {검색할 환자명},
      "dateBirth": {검색할 생년월일}
    }
    ```

  Response `200(OK)`

    ```
    {
      "status": "OK",
      "message": "전체 환자 조회 성공",
      "data": [
          {
              "id": 1,
              "name": "오지웅",
              "registrationNumber": "2022000001",
              "genderCode": "M",
              "dateBirth": "1994-04-12",
              "mobilePhoneNumber": "010-1234-1234",
              "recentReceptionDate": "2022-04-26"
          },
          {
              "id": 2,
              "name": "유재석",
              "registrationNumber": "2022000001",
              "genderCode": "M",
              "dateBirth": "1954-06-21",
              "mobilePhoneNumber": "010-1345-1345",
              "recentReceptionDate": "2022-04-26"
          },
          {
              "id": 3,
              "name": "조세호",
              "registrationNumber": "2022000001",
              "genderCode": "M",
              "dateBirth": "1964-04-01",
              "mobilePhoneNumber": "010-3453-2343",
              "recentReceptionDate": ""
          }
      ]
  }
  ```
