## Tiny_EMR_Service

### 프로젝트 환경
- Language : Java 11.0.10
- Build : gradle
- FrameWork : Spring Boot 2.6.7

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

### RESTful API 명세서 정의

### `POST`  /p**atient**

- 환자 등록

  Request

    ```
    {
       hospitalId, // 병원ID
       name, // 환자명
       genderCode, // 성별코드 
       dateBirth, // 생년원일 (optinal)
       mobilePhoneNumber // 휴대전화번호 (optinal)
    }
    ```

  Response `201(Created)`

    ```
    {
       patient
    }
    ```


#### `PUT` /p**atient/:id**

- 환자 수정

  Request

    ```
    {
       hospitalId, // 병원ID
       name, // 환자명
       genderCode, // 성별코드 
       dateBirth, // 생년원일 (optinal)
       mobilePhoneNumber // 휴대전화번호 (optinal)
    }
    ```

  Response `200(OK)`

    ```
    {
       patient
    }
    ```


#### `DELETE` /p**atient**/:**id**

- 환자 삭제

  Response `204(No Content)`


#### `GET` /p**atient/**:**id**

- 환자 id 조회

  Response `200(OK)`

    ```
    {
       patient
    }
    ```


#### `GET` /p**atient**

- 전체 환자 조회

  Response `200(OK)`

    ```
    {
    	[patient, patient ...]
    }
    ```