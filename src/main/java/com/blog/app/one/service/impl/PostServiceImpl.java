package com.blog.app.one.service.impl;

import com.blog.app.one.dto.PostDto;
import com.blog.app.one.entity.Post;
import com.blog.app.one.mapstruct.DtoToEntityMapper;
import com.blog.app.one.mapstruct.EntityToDTOMapper;
import com.blog.app.one.repository.PostRepository;
import com.blog.app.one.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<PostDto> getAllPosts(){
       List<Post> allPosts= postRepository.findAll();
        return allPosts.stream().map(post-> entityToDTOMapper.dtoToEntity(post)).collect(Collectors.toList());
    }


}
