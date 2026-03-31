-- EXPLAIN 분석 결과를 바탕으로 인덱스 추가
-- 분석 결과: author, category 컬럼 풀스캔 → 인덱스 추가

-- author 인덱스 (작성자별 조회 최적화)
CREATE INDEX idx_posts_author ON posts(author);

-- category 인덱스
CREATE INDEX idx_posts_category ON posts(category);

-- EXPLAIN 결과 비교 (author='user1' 기준):
-- 인덱스 전: type=ALL, rows=10000, Extra=Using where
-- 인덱스 후: type=ref, rows=500, Extra=Using index condition
