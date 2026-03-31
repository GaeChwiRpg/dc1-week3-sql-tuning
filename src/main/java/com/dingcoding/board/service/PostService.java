package com.dingcoding.board.service;

import com.dingcoding.board.entity.Post;
import com.dingcoding.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<Post> getPostsByAuthor(String author) {
        return postRepository.findByAuthor(author);
    }

    @Transactional(readOnly = true)
    public List<Post> getPostsByCategory(String category) {
        return postRepository.findByCategory(category);
    }

    @Transactional(readOnly = true)
    public List<Post> getRecentPosts() {
        return postRepository.findRecentPosts();
    }

    @Transactional(readOnly = true)
    public List<Post> searchPosts(String author, String category) {
        if (author != null && category != null) {
            return postRepository.findByAuthorAndCategory(author, category);
        } else if (author != null) {
            return postRepository.findByAuthor(author);
        } else if (category != null) {
            return postRepository.findByCategory(category);
        }
        return postRepository.findAll();
    }
}
