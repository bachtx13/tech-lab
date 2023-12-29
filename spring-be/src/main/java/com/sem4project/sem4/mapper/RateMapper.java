package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.RateDto;
import com.sem4project.sem4.entity.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RateMapper extends BaseMapper<Rate, RateDto>{
    RateMapper INSTANCE = Mappers.getMapper(RateMapper.class);

    @Mappings({
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    RateDto toDto(Rate rate);

    @Mappings({
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    List<RateDto> toListDto(List<Rate> rates);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "user", ignore = true),
    })
    Rate toEntity(RateDto rateDto);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "user", ignore = true),
    })
    List<Rate> toListEntity(List<RateDto> rateDtos);

    @Mappings({
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    void transferToDto(@MappingTarget RateDto rateDto, Rate rate);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "user", ignore = true),
    })
    void transferToEntity(@MappingTarget Rate rate, RateDto rateDto);
}
