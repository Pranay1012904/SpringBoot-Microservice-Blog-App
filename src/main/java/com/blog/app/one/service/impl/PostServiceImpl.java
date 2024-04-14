package com.blog.app.one.service.impl;

import com.blog.app.one.dto.PostDto;
import com.blog.app.one.dto.PostPaginationResponse;
import com.blog.app.one.entity.Post;
import com.blog.app.one.exception.ResourceNotFoundException;
import com.blog.app.one.mapstruct.DtoToEntityMapper;
import com.blog.app.one.mapstruct.EntityToDTOMapper;
import com.blog.app.one.repository.PostRepository;
import com.blog.app.one.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
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
    @Override
    public PostPaginationResponse getAllPosts(
            int pageNo, int pageSize
    ){
        //create pageable instance
        Pageable pageable= PageRequest.of(pageNo, pageSize);
        Page<Post> allPosts= postRepository.findAll(pageable);
        List<Post> listOfPosts=allPosts.getContent();
        List<PostDto> content= listOfPosts.stream().map(post-> entityToDTOMapper.dtoToEntity(post)).collect(Collectors.toList());
        PostPaginationResponse paginationResponse=new PostPaginationResponse();
        paginationResponse.setContent(content);
        paginationResponse.setPageNo(allPosts.getNumber());
        paginationResponse.setPageSize(allPosts.getSize());
        paginationResponse.setTotalElements(allPosts.getTotalElements());
        paginationResponse.setTotalPages(allPosts.getTotalPages());
        paginationResponse.setLast(allPosts.isLast());
        return paginationResponse;
    }
    @Override
    public PostDto findPostById(Long id){
       Post fetchedPost= postRepository.findPostById(id).orElseThrow(()-> new ResourceNotFoundException("POST","ID",id.toString()));
        return entityToDTOMapper.dtoToEntity(fetchedPost);

    }

}
