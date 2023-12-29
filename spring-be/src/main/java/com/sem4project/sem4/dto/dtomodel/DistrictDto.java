package com.sem4project.sem4.dto.dtomodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistrictDto extends BaseDto{
    private String name;
    private ProvinceDto province;
    private List<BranchDto> branches;
}
