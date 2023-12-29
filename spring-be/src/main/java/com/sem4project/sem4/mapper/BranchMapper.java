package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.BranchDto;
import com.sem4project.sem4.entity.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface BranchMapper extends BaseMapper<Branch, BranchDto>{
    BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);

    @Mappings({
            @Mapping(target = "district", ignore = true),
            @Mapping(target = "province", ignore = true),
            @Mapping(target = "branchProducts", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    BranchDto toDto(Branch branch);
    @Mappings({
            @Mapping(target = "district", ignore = true),
            @Mapping(target = "province", ignore = true),
            @Mapping(target = "branchProducts", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    List<BranchDto> toListDto(List<Branch> branches);
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "district", ignore = true),
            @Mapping(target = "province", ignore = true),
            @Mapping(target = "branchProducts", ignore = true),
    })
    Branch toEntity(BranchDto branchDto);
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "district", ignore = true),
            @Mapping(target = "province", ignore = true),
            @Mapping(target = "branchProducts", ignore = true),
    })
    List<Branch> toListEntity(List<BranchDto> branchDtos);
    @Mappings({
            @Mapping(target = "district", ignore = true),
            @Mapping(target = "province", ignore = true),
            @Mapping(target = "branchProducts", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
    })
    void transferToDto(@MappingTarget BranchDto branchDto, Branch branch);
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "district", ignore = true),
            @Mapping(target = "province", ignore = true),
            @Mapping(target = "branchProducts", ignore = true),
    })
    void transferToEntity(@MappingTarget Branch branch, BranchDto branchDto);

}
