package com.blog.app.one.mapstruct;

import com.blog.app.one.dto.CommentDto;
import com.blog.app.one.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class,componentModel ="spring")
public interface CommentDtoToEntity {
    @Mapping(source="id", target="id")
    @Mapping(source="name", target="name")
    @Mapping(source="email", target="email")
    @Mapping(source="content", target="body")
    Comment dtoToEntity(CommentDto commentDto);
}
