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
public class InvoiceDto extends BaseDto{
    private String fullName;
    private String email;
    private String phone;
    private UserDto user;
    private List<ProductDto> products;
    private ProvinceDto province;
    private DistrictDto district;
    private String address;
    private String note;
    private TransactionalDto transactional;
}
