package com.dingcoding.board.repository;

import com.dingcoding.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // 작성자별 게시글 조회
    // EXPLAIN 결과: type=ALL, rows=10000 → 풀스캔 발생
    // 개선 필요: author 컬럼에 인덱스 추가
    List<Post> findByAuthor(String author);

    // 카테고리별 조회
    // EXPLAIN 결과: type=ALL, rows=10000
    List<Post> findByCategory(String category);

    // 최근 게시글 조회 - ORDER BY created_at DESC
    // EXPLAIN 결과: Using filesort → 인덱스 없으면 정렬 비용 큼
    @Query("SELECT p FROM Post p ORDER BY p.createdAt DESC")
    List<Post> findRecentPosts();

    // 작성자 + 카테고리 복합 조건 검색
    // 인덱스 전략 고민 중: author, category 각각? 아니면 복합 인덱스?
    @Query("SELECT p FROM Post p WHERE p.author = :author AND p.category = :category")
    List<Post> findByAuthorAndCategory(@Param("author") String author,
                                        @Param("category") String category);
}
