package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.PostDto;
import com.sem4project.sem4.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface PostMapper extends BaseMapper<Post, PostDto> {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
    @Mappings({
            @Mapping(target = "comments", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    PostDto toDto(Post post);

    @Mappings({
            @Mapping(target = "comments", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    List<PostDto> toListDto(List<Post> posts);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "comments", ignore = true),
    })
    Post toEntity(PostDto postDto);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "comments", ignore = true),
    })
    List<Post> toListEntity(List<PostDto> postDtos);

    @Mappings({
            @Mapping(target = "comments", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    void transferToDto(@MappingTarget PostDto postDto, Post post);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "comments", ignore = true),
    })
    void transferToEntity(@MappingTarget Post post, PostDto postDto);
}
