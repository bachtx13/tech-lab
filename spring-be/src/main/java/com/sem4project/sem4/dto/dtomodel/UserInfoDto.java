package com.sem4project.sem4.dto.dtomodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import java.time.Instant;
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public class UserInfoDto extends BaseDto{
    @Length(min = 8)
    private String fullName;
    private String phone;
    private Instant dob;
    private ProvinceDto province;
    private DistrictDto district;
    private String address;
}
