# Spring Boot Web Service Assignment

Spring Boot를 이용한 게시글 REST API 구현 과제입니다.

## 1. 프로젝트 개요

Spring Boot와 Spring Data JPA를 사용하여 게시글 CRUD REST API를 구현했습니다.

주요 구현 내용:

* REST API 구현
* 게시글 생성, 조회, 수정, 삭제
* Spring Data JPA를 이용한 데이터 저장
* 공통 응답 형식 적용
* 예외 처리
* 요청/응답 로깅 Middleware 구현
* HTTP 응답 상태 코드 처리

## 2. 개발 환경

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* Lombok
* H2 Database
* Gradle

## 3. API 목록

### POST

1. 게시글 생성

`POST /api/posts`

요청 예시:

```json
{
  "title": "첫 번째 게시글",
  "content": "게시글 내용입니다."
}
```

2. 게시글 복제

`POST /api/posts/{id}/duplicate`

### GET

1. 게시글 전체 조회

`GET /api/posts`

2. 게시글 단건 조회

`GET /api/posts/{id}`

### PUT

1. 게시글 전체 수정

`PUT /api/posts/{id}`

요청 예시:

```json
{
  "title": "수정된 제목",
  "content": "수정된 내용입니다."
}
```

2. 게시글 제목 수정

`PUT /api/posts/{id}/title`

요청 예시:

```json
{
  "title": "수정된 제목"
}
```

### DELETE

1. 게시글 단건 삭제

`DELETE /api/posts/{id}`

2. 게시글 전체 삭제

`DELETE /api/posts`

## 4. 공통 응답 형식

모든 API의 응답 형식을 다음과 같이 통일했습니다.

성공 응답:

```json
{
  "status": "success",
  "data": {}
}
```

오류 응답:

```json
{
  "status": "error",
  "data": {
    "message": "오류 메시지"
  }
}
```

## 5. HTTP 응답 상태 코드

### 2xx

* 200 OK : 조회, 수정, 삭제 성공
* 201 Created : 게시글 생성 및 복제 성공

### 4xx

* 400 Bad Request : 잘못된 요청
* 404 Not Found : 존재하지 않는 게시글

### 5xx

* 500 Internal Server Error : 서버 내부 오류
* 503 Service Unavailable : 서비스 이용 불가

## 6. 예외 처리

GlobalExceptionHandler를 사용하여 예외를 공통적으로 처리했습니다.

처리하는 예외:

* PostNotFoundException
* IllegalArgumentException
* ServiceUnavailableException
* 기타 예외

오류가 발생하더라도 공통 응답 형식인 `status`와 `data` 구조를 유지하도록 구현했습니다.

## 7. Middleware

RequestLoggingFilter를 사용하여 HTTP 요청과 응답을 로그로 기록했습니다.

예시:

```text
[REQUEST] POST /api/posts
[RESPONSE] 201 25ms
```

## 8. 데이터베이스

H2 Database를 사용하여 게시글 데이터를 저장합니다.

게시글은 다음 정보를 가집니다.

* id
* title
* content
* createdAt
* updatedAt

## 9. 테스트

터미널의 curl 명령어를 이용하여 API를 테스트했습니다.

게시글 생성:

```bash
curl -X POST http://localhost:8080/api/posts -H "Content-Type: application/json" -d '{"title":"테스트 게시글","content":"테스트 내용입니다."}'
```

게시글 전체 조회:

```bash
curl -i http://localhost:8080/api/posts
```

게시글 단건 조회:

```bash
curl -i http://localhost:8080/api/posts/1
```

게시글 수정:

```bash
curl -X PUT http://localhost:8080/api/posts/1 -H "Content-Type: application/json" -d '{"title":"수정된 게시글","content":"수정된 내용입니다."}'
```

제목만 수정:

```bash
curl -X PUT http://localhost:8080/api/posts/1/title -H "Content-Type: application/json" -d '{"title":"제목만 수정했습니다"}'
```

게시글 삭제:

```bash
curl -X DELETE http://localhost:8080/api/posts/1
```

전체 게시글 삭제:

```bash
curl -X DELETE http://localhost:8080/api/posts
```

400 Bad Request 테스트:

```bash
curl -i -X POST http://localhost:8080/api/posts -H "Content-Type: application/json" -d '{"title":"","content":"내용입니다."}'
```

404 Not Found 테스트:

```bash
curl -i http://localhost:8080/api/posts/9999
```

500 Internal Server Error 테스트:

```bash
curl -i -X POST http://localhost:8080/api/posts/3/duplicate -H "X-Internal-Error: true"
```

503 Service Unavailable 테스트:

```bash
curl -i -X POST http://localhost:8080/api/posts/3/duplicate -H "X-Service-Unavailable: true"
```

## 10. 실행 방법

Spring Boot 애플리케이션을 실행한 후 다음 주소를 사용할 수 있습니다.

`http://localhost:8080`

게시글 REST API는 다음 경로를 사용합니다.

`/api/posts`
