package com.blog.app.one.controller;

import com.blog.app.one.entity.Post;
import com.blog.app.one.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/post/api")
public class PostController {
    private PostService postService;
    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        Post savedPost=postService.createPost(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }
}
