package com.sem4project.sem4.dto.dtomodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public class ProductDto extends BaseDto{
    @NotEmpty
    private String title;
    private String productCode;
    private String warrantyPeriod;
    @NotNull(message = "Cost must not be null")
    private Double cost;
    @NotNull(message = "Promotional must not be null")
    private Double promotional;
    private boolean status;
    private List<PhotoDto> photos;
    private String video;
    private List<GiftDto> gifts;
    @NotEmpty(message = "Specifications must not be empty")
    @NotNull(message = "Specifications must not be null")
    private List<SpecificationDto> specifications;
    private PostDto post;
    @NotEmpty(message = "Categories must not be empty")
    @NotNull(message = "Categories must not be null")
    private List<CategoryDto> categories;
    private BrandDto brand;
    private List<RateDto> rates;
    private List<BranchProductDto> branchProducts;
    private List<CommentDto> comments;
}
