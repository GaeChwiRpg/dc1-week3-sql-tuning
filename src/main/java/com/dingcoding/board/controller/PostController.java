package com.dingcoding.board.controller;

import com.dingcoding.board.entity.Post;
import com.dingcoding.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getPosts(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category) {
        return ResponseEntity.ok(postService.searchPosts(author, category));
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Post>> getRecentPosts() {
        return ResponseEntity.ok(postService.getRecentPosts());
    }
}
