package com.sem4project.sem4.dto.dtomodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto extends BaseDto{
    private UUID id;
    private String name;
    private List<CategoryDto> categories;
    private CategoryDto parentCategory;
    private List<ProductDto> products;
}
