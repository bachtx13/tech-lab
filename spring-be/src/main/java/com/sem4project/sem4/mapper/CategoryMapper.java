package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.CategoryDto;
import com.sem4project.sem4.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category, CategoryDto>{
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mappings({
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "parentCategory", ignore = true),
            @Mapping(target = "products", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    CategoryDto toDto(Category category);

    @Mappings({
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "parentCategory", ignore = true),
            @Mapping(target = "products", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    List<CategoryDto> toListDto(List<Category> categories);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "parentCategory", ignore = true),
            @Mapping(target = "products", ignore = true),
    })
    Category toEntity(CategoryDto categoryDto);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "parentCategory", ignore = true),
            @Mapping(target = "products", ignore = true),
    })
    List<Category> toListEntity(List<CategoryDto> categoryDtos);

    @Mappings({
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "parentCategory", ignore = true),
            @Mapping(target = "products", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    void transferToDto(@MappingTarget CategoryDto categoryDto, Category category);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "parentCategory", ignore = true),
            @Mapping(target = "products", ignore = true),
    })
    void transferToEntity(@MappingTarget Category category, CategoryDto categoryDto);
}
