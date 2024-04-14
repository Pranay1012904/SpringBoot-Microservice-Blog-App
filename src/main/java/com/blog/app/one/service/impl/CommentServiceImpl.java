package com.blog.app.one.service.impl;

import com.blog.app.one.dto.CommentDto;
import com.blog.app.one.entity.Comment;
import com.blog.app.one.entity.Post;
import com.blog.app.one.exception.CommentApiException;
import com.blog.app.one.exception.ResourceNotFoundException;
import com.blog.app.one.mapstruct.CommentDtoToEntity;
import com.blog.app.one.mapstruct.CommentEntityToDto;
import com.blog.app.one.repository.CommentRepository;
import com.blog.app.one.repository.PostRepository;
import com.blog.app.one.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private CommentDtoToEntity dtoToEntity;
    private CommentEntityToDto entityToDto;
    private PostRepository postRepository;
    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
         Comment comment= dtoToEntity.dtoToEntity(commentDto);
        Post post=postRepository.findPostById(postId).orElseThrow(()-> new ResourceNotFoundException("POST", "ID", postId.toString()));
        comment.setPost(post);
        return entityToDto.dtoToEntity(commentRepository.save(comment));
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
      List<Comment> fetchedComments= commentRepository.findByPostId(postId);
     return fetchedComments.stream().map(comment -> entityToDto.dtoToEntity(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post=postRepository.findPostById(postId).orElseThrow(()->new ResourceNotFoundException("POST", "ID", postId.toString()));
        Comment comment=commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("COMMENT", "ID", commentId.toString()));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new CommentApiException(HttpStatus.NOT_FOUND, String.format("COMMENT WITH ID %s DOES NOT BELONG TO POST WITH ID %s",postId,commentId) );
        }
        return entityToDto.dtoToEntity(comment);
    }
}
