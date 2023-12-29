package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.UserInfoDto;
import com.sem4project.sem4.entity.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo, UserInfoDto>{

    UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);
    @Mappings({
            @Mapping(target = "province", ignore = true),
            @Mapping(target = "district", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    UserInfoDto toDto(UserInfo userInfo);
    @Mappings({
            @Mapping(target = "province", ignore = true),
            @Mapping(target = "district", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    List<UserInfoDto> toListDto(List<UserInfo> userInfos);
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "province", ignore = true),
            @Mapping(target = "district", ignore = true),
    })
    UserInfo toEntity(UserInfoDto userInfoDto);
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "province", ignore = true),
            @Mapping(target = "district", ignore = true),
    })
    List<UserInfo> toListEntity(List<UserInfoDto> userInfoDtos);
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "province", ignore = true),
            @Mapping(target = "district", ignore = true),
    })
    void transferToEntity(@MappingTarget UserInfo userInfo, UserInfoDto userInfoDto);

    @Override
    @Mappings({
            @Mapping(target = "province", ignore = true),
            @Mapping(target = "district", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    void transferToDto(@MappingTarget UserInfoDto userInfoDto, UserInfo userInfo);
}
