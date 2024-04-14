package com.blog.app.one.service;

import com.blog.app.one.dto.PostDto;
import com.blog.app.one.entity.Post;

import java.util.List;

public interface PostService {
    public PostDto createPost(PostDto post);
    public List<PostDto> getAllPosts();
    public PostDto findPostById(Long id);
}
