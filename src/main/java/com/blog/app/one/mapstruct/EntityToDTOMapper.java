package com.blog.app.one.mapstruct;

import com.blog.app.one.dto.PostDto;
import com.blog.app.one.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class,componentModel ="spring")
public interface EntityToDTOMapper {
    @Mapping(source="id", target="id")
    @Mapping(source="title", target="title")
    @Mapping(source="description", target="description")
    @Mapping(source="content", target="content")
    PostDto dtoToEntity(Post post);
}
