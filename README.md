# Week 3: SQL 튜닝 기초

## 미션 목표
기존 Spring Boot 게시판 API에 **인덱스를 설계하고 쿼리를 최적화**합니다.
EXPLAIN으로 실행 계획을 분석하고, 인덱스 적용 전후를 비교하는 것이 핵심입니다.

## 요구사항

### 기능 요구사항
- [ ] 성능이 낮은 쿼리에 EXPLAIN 분석 수행
- [ ] 분석 결과를 바탕으로 인덱스 설계 및 적용
- [ ] 인덱스 적용 전후 쿼리 성능 비교
- [ ] 복합 인덱스가 필요한 경우 근거 설명

### 기술 요구사항
- EXPLAIN / EXPLAIN ANALYZE 실행 및 결과 해석
- 단일 인덱스 vs 복합 인덱스 선택 근거 제시
- Flyway 마이그레이션으로 인덱스 추가 (`V2__add_indexes.sql`)
- 최적화 전후 type, rows, Extra 컬럼 변화 비교

## 환경 설정

### 1. MySQL 설치 및 데이터베이스 생성
```sql
CREATE DATABASE board_db DEFAULT CHARACTER SET utf8mb4;
```

### 2. application.yml 수정
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/board_db
    username: root
    password: YOUR_PASSWORD
```

### 3. 실행
```bash
./mvnw spring-boot:run
```
- 애플리케이션: http://localhost:8080

> **Tip**: Docker로 MySQL을 실행하면 편리합니다.
> ```bash
> docker run -d --name mysql-board -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=board_db -p 3306:3306 mysql:8
> ```

## 성능 개선 대상 쿼리

| 메서드 | 현재 문제 | 개선 방향 |
|--------|-----------|-----------|
| `findByAuthor` | type=ALL (풀스캔) | author 인덱스 추가 |
| `findByCategory` | type=ALL (풀스캔) | category 인덱스 추가 |
| `findRecentPosts` | filesort 발생 | created_at 인덱스 추가 |

## 제출 방법
1. 이 레포를 fork 합니다.
2. `feature/{이름}` 브랜치를 생성합니다.
3. 인덱스를 설계하고 `src/main/resources/db/migration/V2__add_indexes.sql`에 추가합니다.
4. Pull Request를 생성합니다.
5. PR 설명에 다음을 반드시 포함합니다:
   - **EXPLAIN 분석 결과** (인덱스 전후 type, rows, Extra 비교)
   - **인덱스 선택 근거** (왜 이 컬럼에 인덱스를 추가했는지)
   - **복합 인덱스 사용 여부와 이유**

## 평가 기준

| 항목 | 기준 | 비중 |
|------|------|------|
| 네이밍 | 변수/메서드/클래스 네이밍 | 20% |
| 구조 | 코드 구조 | 20% |
| 쿼리 최적화 | EXPLAIN 분석과 인덱스 전략이 적절한가 | 40% |
| Spring 컨벤션 | Spring Boot 컨벤션을 따르는가 | 20% |
