package com.blog.app.one.controller;

import com.blog.app.one.dto.PostDto;
import com.blog.app.one.dto.PostPaginationResponse;
import com.blog.app.one.entity.Post;
import com.blog.app.one.service.PostService;
import com.blog.app.one.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/post/api")
public class PostController {
    private PostService postService;
    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto post){
        PostDto savedPost=postService.createPost(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }
    @GetMapping("/get/allPosts")
    public ResponseEntity<PostPaginationResponse> getAllPost(
            @RequestParam(value = Constants.PAGE_NUMBER, defaultValue = Constants.PAGE_DEFAULT_VALUE, required = false) int pageNo,
            @RequestParam(value= Constants.PAGE_SIZE, defaultValue = Constants.PAGE_DEFAULT_ELEMENTS, required = false) int pageSize,
            @RequestParam(value = Constants.SORT_BY, defaultValue = Constants.SORT_DEFAULT_VALUE, required = false) String sortBy
    ){
        PostPaginationResponse allPosts= postService.getAllPosts(pageNo, pageSize, sortBy);
       return new ResponseEntity<>(allPosts,HttpStatus.OK);
    }
    @GetMapping("/get/byId")
    public ResponseEntity<PostDto> getPostById(@RequestParam Long id){
       PostDto fetchedPost= postService.findPostById(id);
       return new ResponseEntity<>(fetchedPost,HttpStatus.FOUND);
    }
}
