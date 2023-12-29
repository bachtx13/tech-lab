package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.ProductDto;
import com.sem4project.sem4.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface ProductMapper extends BaseMapper<Product, ProductDto> {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    @Mappings({
            @Mapping(target = "photos", ignore = true),
            @Mapping(target = "gifts", ignore = true),
            @Mapping(target = "specifications", ignore = true),
            @Mapping(target = "post", ignore = true),
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "brand", ignore = true),
            @Mapping(target = "rates", ignore = true),
            @Mapping(target = "branchProducts", ignore = true),
            @Mapping(target = "comments", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    ProductDto toDto(Product product);

    @Mappings({
            @Mapping(target = "photos", ignore = true),
            @Mapping(target = "gifts", ignore = true),
            @Mapping(target = "specifications", ignore = true),
            @Mapping(target = "post", ignore = true),
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "brand", ignore = true),
            @Mapping(target = "rates", ignore = true),
            @Mapping(target = "branchProducts", ignore = true),
            @Mapping(target = "comments", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    List<ProductDto> toListDto(List<Product> products);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "photos", ignore = true),
            @Mapping(target = "gifts", ignore = true),
            @Mapping(target = "specifications", ignore = true),
            @Mapping(target = "post", ignore = true),
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "brand", ignore = true),
            @Mapping(target = "rates", ignore = true),
            @Mapping(target = "branchProducts", ignore = true),
            @Mapping(target = "comments", ignore = true),
    })
    Product toEntity(ProductDto productDto);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "photos", ignore = true),
            @Mapping(target = "gifts", ignore = true),
            @Mapping(target = "specifications", ignore = true),
            @Mapping(target = "post", ignore = true),
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "brand", ignore = true),
            @Mapping(target = "rates", ignore = true),
            @Mapping(target = "branchProducts", ignore = true),
            @Mapping(target = "comments", ignore = true),
    })
    List<Product> toListEntity(List<ProductDto> productDtos);

    @Mappings({
            @Mapping(target = "photos", ignore = true),
            @Mapping(target = "gifts", ignore = true),
            @Mapping(target = "specifications", ignore = true),
            @Mapping(target = "post", ignore = true),
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "brand", ignore = true),
            @Mapping(target = "rates", ignore = true),
            @Mapping(target = "branchProducts", ignore = true),
            @Mapping(target = "comments", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    void transferToDto(@MappingTarget ProductDto productDto, Product product);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "photos", ignore = true),
            @Mapping(target = "gifts", ignore = true),
            @Mapping(target = "specifications", ignore = true),
            @Mapping(target = "post", ignore = true),
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "brand", ignore = true),
            @Mapping(target = "rates", ignore = true),
            @Mapping(target = "branchProducts", ignore = true),
            @Mapping(target = "comments", ignore = true),
    })
    void transferToEntity(@MappingTarget Product product, ProductDto productDto);
}
