package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.SpecificationDto;
import com.sem4project.sem4.entity.Specification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SpecificationMapper extends BaseMapper<Specification, SpecificationDto>{
    SpecificationMapper INSTANCE = Mappers.getMapper(SpecificationMapper.class);

    @Mappings({
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    SpecificationDto toDto(Specification specification);

    @Mappings({
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    List<SpecificationDto> toListDto(List<Specification> specifications);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    Specification toEntity(SpecificationDto specificationDto);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    List<Specification> toListEntity(List<SpecificationDto> specificationDtos);

    @Mappings({
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    void transferToDto(@MappingTarget SpecificationDto specificationDto, Specification specification);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    void transferToEntity(@MappingTarget Specification specification, SpecificationDto specificationDto);
}
