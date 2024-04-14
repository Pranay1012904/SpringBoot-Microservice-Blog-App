package com.blog.app.one.controller;

import com.blog.app.one.dto.CommentDto;
import com.blog.app.one.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments/api")
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;
    @PostMapping("/create/{id}")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value= "id") Long postId, @RequestBody CommentDto commentDto){
       CommentDto savedComment= commentService.createComment(postId,commentDto);
       return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }
    @GetMapping("/get/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable(value= "postId") Long postId){
       List<CommentDto> fetchedPosts= commentService.getCommentsByPostId(postId);
       return new ResponseEntity<>(fetchedPosts,HttpStatus.OK);
    }
    @GetMapping("/get/post/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") Long postId,
                                                     @PathVariable(value = "commentId") Long commentId){
        CommentDto comment=commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(comment,HttpStatus.FOUND);
    }
}
