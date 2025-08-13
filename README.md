# JPA 연관관계 CRUD & 인증/인가 ✨

---

## 프로젝트 소개 🚀

이 프로젝트는 **Spring Boot**와 **JPA(Java Persistence API)**를 활용하여 일정 및 사용자 관리 기능을 구현한 백엔드 애플리케이션입니다. 기본적인 **CRUD(Create, Read, Update, Delete)** 작업은 물론, 사용자 간의 **연관관계 매핑**, 안전한 **회원가입 및 로그인(인증)** 시스템을 구축하는 데 중점을 두었습니다. 또한, **비밀번호 암호화**와 **다양한 예외 처리**를 통해 견고하고 보안성 높은 서비스를 제공합니다.

---

## 주요 기능 (Levels) 🎯

이 프로젝트는 다음과 같은 단계별 기능을 포함합니다.

### Lv 1. 일정 CRUD (필수) 📅

* **일정 생성, 조회, 수정, 삭제 기능**을 제공합니다.
* **필드**: 할 일 제목, 할 일 내용, 작성 유저명, 작성일, 수정일
* **JPA Auditing**: 작성일(`createdAt`), 수정일(`modifiedAt`) 필드는 JPA Auditing을 활용하여 자동으로 관리됩니다.

### Lv 2. 유저 CRUD (필수) 👤

* **유저 생성, 조회, 수정, 삭제 기능**을 제공합니다.
* **필드**: 유저명, 이메일, 작성일, 수정일
* **JPA Auditing**: 작성일(`createdAt`), 수정일(`modifiedAt`) 필드는 JPA Auditing을 활용하여 자동으로 관리됩니다.
* **연관관계 구현**: 일정은 이제 작성 유저명 필드 대신 **유저 고유 식별자 필드**를 통해 해당 일정을 작성한 유저와 연관됩니다.

### Lv 3. 회원가입 (필수) 📝

* 유저 엔티티에 **비밀번호 필드**를 추가하여 회원가입 시 비밀번호를 입력받습니다.
* 비밀번호 암호화는 Lv 6. 도전 기능에서 수행됩니다.

### Lv 4. 로그인 (인증) (필수) 🔑

* **Cookie/Session**을 활용하여 로그인 기능을 구현합니다.
* **`Filter`**를 사용하여 인증 처리를 수행하며, `@Configuration`을 통해 필터를 등록합니다.
* **조건**: 이메일과 비밀번호를 활용하여 로그인합니다. 회원가입, 로그인 요청은 인증 처리에서 제외됩니다.
* **예외처리**: 로그인 시 이메일과 비밀번호가 일치하지 않을 경우 **HTTP Status code 401 (Unauthorized)**을 반환합니다.

---

## 도전 기능 가이드 💡

### Lv 5. 다양한 예외처리 적용하기 ✅

* **Validation**을 활용하여 다양한 비즈니스 및 입력 값에 대한 예외 처리를 적용합니다.
* 예시: 할 일 제목은 특정 글자 수 이내, 유저명은 특정 글자 수 이내 등의 제약 조건을 설정하고 유효성 검증을 수행합니다.

### Lv 6. 비밀번호 암호화 🔒

* Lv 3에서 추가한 비밀번호 필드에 들어가는 비밀번호를 암호화합니다.
* **`PasswordEncoder`**를 직접 구현하여 사용하며, `at.favre.lib:bcrypt` 라이브러리를 활용합니다. (제공된 `PasswordEncoder` 코드를 참조)

---

## 기술 스택 🛠️

* **백엔드**: Java 17+, Spring Boot 3.5.4
* **데이터베이스**: MySQL
* **ORM**: JPA, Hibernate
* **보안**: Spring Security (기반 필터), `at.favre.lib:bcrypt`
* **기타**: Lombok (코드 간결화)

## API 엔드포인트 📡

애플리케이션이 실행되면, 다음 기본 엔드포인트를 통해 API를 테스트할 수 있습니다.

* **인증**:
    * `POST /login`: 사용자 로그인 (이메일, 비밀번호)

* **유저**:
    * `POST /users/createUser`: 새 유저 생성
    * `GET /users/allUser`: 모든 유저 조회 (인증 필요)
    * `GET /users/findOneUser/{id}`: 특정 유저 조회 (인증 필요)
    * `PUT /users/updateUser/{id}`: 특정 유저 정보 수정 (인증 필요)
    * `DELETE /users/deleteUser/{id}`: 특정 유저 삭제 (인증 필요)

* **일정**:
    * `POST /createSchedule`: 새 일정 생성
    * `GET /findAllSchedule`: 모든 일정 조회
    * `GET /findOneSchedule/{id}`: 특정 일정 조회
    * `PUT /updateSchedule/{id}`: 특정 일정 수정 (비밀번호 확인)
    * `DELETE /deleteSchedule/{id}`: 특정 일정 삭제 (비밀번호 확인)

---
