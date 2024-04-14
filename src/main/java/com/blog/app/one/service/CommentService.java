package com.blog.app.one.service;

import com.blog.app.one.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(Long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(Long postId);
}
