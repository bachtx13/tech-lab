package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.ProvinceDto;
import com.sem4project.sem4.entity.Province;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProvinceMapper extends BaseMapper<Province, ProvinceDto>{
    ProvinceMapper INSTANCE = Mappers.getMapper(ProvinceMapper.class);
    @Mappings({
            @Mapping(target = "districts", ignore = true),
            @Mapping(target = "branches", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    ProvinceDto toDto(Province province);
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "districts", ignore = true),
            @Mapping(target = "branches", ignore = true),
    })
    Province toEntity(ProvinceDto provinceDto);
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "districts", ignore = true),
            @Mapping(target = "branches", ignore = true),
    })
    void transferToEntity(@MappingTarget Province province, ProvinceDto provinceDto);

    @Mappings({
            @Mapping(target = "districts", ignore = true),
            @Mapping(target = "branches", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    List<ProvinceDto> toListDto(List<Province> provinces);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "districts", ignore = true),
            @Mapping(target = "branches", ignore = true),
    })
    List<Province> toListEntity(List<ProvinceDto> provinceDtos);

    @Override
    @Mappings({
            @Mapping(target = "districts", ignore = true),
            @Mapping(target = "branches", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    void transferToDto(@MappingTarget ProvinceDto provinceDto, Province province);
}