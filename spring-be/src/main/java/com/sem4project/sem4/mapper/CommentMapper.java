package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.CommentDto;
import com.sem4project.sem4.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface CommentMapper extends BaseMapper<Comment, CommentDto>{
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    @Mappings({
            @Mapping(target = "post", ignore = true),
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    CommentDto toDto(Comment comment);

    @Mappings({
            @Mapping(target = "post", ignore = true),
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    List<CommentDto> toListDto(List<Comment> comments);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "post", ignore = true),
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "user", ignore = true),
    })
    Comment toEntity(CommentDto commentDto);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "post", ignore = true),
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "user", ignore = true),
    })
    List<Comment> toListEntity(List<CommentDto> commentDtos);


    @Mappings({
            @Mapping(target = "post", ignore = true),
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    void transferToDto(@MappingTarget CommentDto commentDto, Comment comment);


    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "post", ignore = true),
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "user", ignore = true),
    })
    void transferToEntity(@MappingTarget Comment comment, CommentDto commentDto);
}
