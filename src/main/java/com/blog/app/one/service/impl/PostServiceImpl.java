package com.blog.app.one.service.impl;

import com.blog.app.one.entity.Post;
import com.blog.app.one.repository.PostRepository;
import com.blog.app.one.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    public Post createPost(Post post){
        Post savedPost=postRepository.save(post);
        return savedPost;
    }


}
