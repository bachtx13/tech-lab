
package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.PhotoDto;
import com.sem4project.sem4.entity.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PhotoMapper extends BaseMapper<Photo, PhotoDto> {
    PhotoMapper INSTANCE = Mappers.getMapper(PhotoMapper.class);
    @Override
    @Mappings({
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    PhotoDto toDto(Photo photo);

    @Override
    @Mappings({
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    List<PhotoDto> toListDto(List<Photo> photos);

    @Override
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    Photo toEntity(PhotoDto photoDto);

    @Override
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    List<Photo> toListEntity(List<PhotoDto> photoDtos);

    @Override
    @Mappings({
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    void transferToDto(@MappingTarget PhotoDto photoDto, Photo photo);

    @Override
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    void transferToEntity(@MappingTarget Photo photo, PhotoDto photoDto);
}
