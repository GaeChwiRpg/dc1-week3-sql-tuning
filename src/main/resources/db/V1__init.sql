-- 인덱스 없는 초기 스키마 (튜닝 전 상태)
CREATE TABLE posts (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    content     TEXT,
    author      VARCHAR(100) NOT NULL,
    category    VARCHAR(50)  NOT NULL,
    created_at  DATETIME     NOT NULL,
    updated_at  DATETIME     NOT NULL
);

CREATE TABLE comments (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    content    TEXT         NOT NULL,
    author     VARCHAR(100) NOT NULL,
    post_id    BIGINT       NOT NULL,
    created_at DATETIME     NOT NULL,
    FOREIGN KEY (post_id) REFERENCES posts (id)
);
