package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.GiftDto;
import com.sem4project.sem4.entity.Gift;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface GiftMapper extends BaseMapper<Gift, GiftDto> {
    GiftMapper INSTANCE = Mappers.getMapper(GiftMapper.class);
    @Override
    @Mappings({
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    GiftDto toDto(Gift gift);

    @Mappings({
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    @Override
    List<GiftDto> toListDto(List<Gift> gifts);

    @Override
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    Gift toEntity(GiftDto giftDto);

    @Override
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    List<Gift> toListEntity(List<GiftDto> giftDtos);

    @Override
    @Mappings({
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    void transferToDto(@MappingTarget GiftDto giftDto, Gift gift);

    @Override
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    void transferToEntity(@MappingTarget Gift gift, GiftDto giftDto);
}
