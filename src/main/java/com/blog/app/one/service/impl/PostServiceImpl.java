package com.blog.app.one.service.impl;

import com.blog.app.one.dto.PostDto;
import com.blog.app.one.entity.Post;
import com.blog.app.one.mapstruct.DtoToEntityMapper;
import com.blog.app.one.mapstruct.EntityToDTOMapper;
import com.blog.app.one.repository.PostRepository;
import com.blog.app.one.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private DtoToEntityMapper dToEMapper;
    private EntityToDTOMapper entityToDTOMapper;
    @Override
    public PostDto createPost(PostDto postDto){
        Post post= dToEMapper.dtoToEntity(postDto);
        Post savedPost=postRepository.save(post);
        return entityToDTOMapper.dtoToEntity(savedPost);
    }


}
