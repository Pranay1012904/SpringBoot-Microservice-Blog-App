package com.blog.app.one.service;

import com.blog.app.one.dto.PostDto;
import com.blog.app.one.entity.Post;

public interface PostService {
    public PostDto createPost(PostDto post);
}
