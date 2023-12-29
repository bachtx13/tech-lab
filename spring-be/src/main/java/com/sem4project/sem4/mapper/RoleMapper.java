package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.RoleDto;
import com.sem4project.sem4.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role, RoleDto>{

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    @Mappings({
            @Mapping(target = "users", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    RoleDto toDto(Role role);
    @Mappings({
            @Mapping(target = "users", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    List<RoleDto> toListDto(List<Role> roles);
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "users", ignore = true),
    })
    Role toEntity(RoleDto roleDto);
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "users", ignore = true),
    })
    List<Role> toListEntity(List<RoleDto> roleDtos);

    @Override
    @Mappings({
            @Mapping(target = "users", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    void transferToDto(@MappingTarget RoleDto roleDto, Role role);

    @Override
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "users", ignore = true),
    })
    void transferToEntity(@MappingTarget Role role, RoleDto roleDto);
}
