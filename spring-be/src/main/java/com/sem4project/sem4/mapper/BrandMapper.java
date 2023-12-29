package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.BrandDto;
import com.sem4project.sem4.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface BrandMapper extends BaseMapper<Brand, BrandDto>{
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    @Mappings({
            @Mapping(target = "photo", ignore = true),
            @Mapping(target = "products", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    BrandDto toDto(Brand brand);
    @Mappings({
            @Mapping(target = "photo", ignore = true),
            @Mapping(target = "products", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    List<BrandDto> toListDto(List<Brand> brands);
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "photo", ignore = true),
            @Mapping(target = "products", ignore = true),
    })
    Brand toEntity(BrandDto brandDto);
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "photo", ignore = true),
            @Mapping(target = "products", ignore = true),
    })
    List<Brand> toListEntity(List<BrandDto> brandDtos);
    @Mappings({
            @Mapping(target = "photo", ignore = true),
            @Mapping(target = "products", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    void transferToDto(@MappingTarget BrandDto brandDto, Brand brand);
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "photo", ignore = true),
            @Mapping(target = "products", ignore = true),
    })
    void transferToEntity(@MappingTarget Brand brand, BrandDto brandDto);
}
