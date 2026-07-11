# Mission10 - 예외 처리 & FE 연동

멤버(Member)와 과제(Assignment)를 관리하는 Spring Boot REST API 프로젝트다. `@RestControllerAdvice` 기반 전역 예외 처리와 통일된 에러 응답 형식을 도입했고, 제공된 프론트엔드에서 CRUD와 검색 기능을 브라우저로 직접 확인할 수 있다.

작성자: KyeongJun

## 기술 스택

- Java 21
- Spring Boot 3.4.5
- Spring Web, Spring Data JPA
- MySQL 8.x (mysql-connector-j)
- Gradle

## 실행 방법

1. MySQL에 `likelion_pbl` 데이터베이스를 준비한다.
   ```sql
   CREATE DATABASE likelion_pbl;
   ```
2. `resources/application.properties`에서 데이터베이스 계정 정보(`spring.datasource.username`, `spring.datasource.password`)를 본인 환경에 맞게 수정한다.
3. 프로젝트를 실행한다.
   ```bash
   ./gradlew bootRun
   ```
4. 브라우저에서 `http://localhost:8080`에 접속하면 멤버 관리, 과제 관리 화면이 표시된다.

## API 목록

### Member API

| HTTP 메서드 | URI | 설명 |
| --- | --- | --- |
| POST | /members/lions | LION 멤버 등록 |
| POST | /members/staffs | STAFF 멤버 등록 |
| GET | /members | 전체 멤버 조회 |
| GET | /members?part= | 파트별 멤버 필터링 |
| GET | /members/{id} | 멤버 단건 조회 |
| PUT | /members/lions/{id} | LION 멤버 수정 |
| PUT | /members/staffs/{id} | STAFF 멤버 수정 |
| DELETE | /members/{id} | 멤버 삭제 |

### Assignment API

| HTTP 메서드 | URI | 설명 |
| --- | --- | --- |
| POST | /members/{memberId}/assignments | 과제 등록 |
| GET | /assignments | 전체 과제 조회 |
| GET | /members/{memberId}/assignments | 멤버별 과제 조회 |
| GET | /assignments/{id} | 과제 단건 조회 |
| GET | /assignments/search?keyword= | 과제 제목 검색 |
| PUT | /assignments/{id} | 과제 수정 |
| DELETE | /assignments/{id} | 과제 삭제 |

### 에러 응답 형식

모든 에러는 아래 형식으로 통일되어 반환된다.

```json
{
  "status": 404,
  "message": "해당 멤버를 찾을 수 없습니다. id: 999"
}
```

| 상황 | 상태 코드 |
| --- | --- |
| 존재하지 않는 멤버 조회/수정/삭제 | 404 Not Found |
| 존재하지 않는 과제 조회/수정/삭제 | 404 Not Found |
| 중복된 이름으로 멤버 등록 | 409 Conflict |

## 프로젝트 구조

```
Mission10/
├── Mission10Application.java
├── member/
│   ├── controller/MemberController.java
│   ├── service/MemberService.java          # 예외를 던지는 방식으로 리팩토링
│   ├── repository/MemberRepository.java    # findByPart 쿼리 메서드 추가
│   ├── domain/{Member, RoleType}.java
│   └── dto/
├── assignment/
│   ├── controller/AssignmentController.java
│   ├── service/AssignmentService.java      # 예외를 던지는 방식으로 리팩토링
│   ├── repository/AssignmentRepository.java # findByTitleContaining 쿼리 메서드 추가
│   ├── domain/Assignment.java
│   └── dto/
├── global/
│   ├── exception/
│   │   ├── GlobalExceptionHandler.java     # @RestControllerAdvice 전역 예외 처리
│   │   ├── MemberNotFoundException.java
│   │   ├── AssignmentNotFoundException.java
│   │   └── DuplicateMemberException.java
│   └── dto/ErrorResponse.java              # 공통 에러 응답 DTO
└── resources/
    ├── application.properties
    └── static/                             # 프론트엔드
        ├── index.html                      # 탭 전환, HTTP 통신 로그, 토스트 알림
        ├── css/style.css
        └── js/{member, assignment}.js
```
