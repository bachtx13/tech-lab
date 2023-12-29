package com.sem4project.sem4.dto.dtomodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sem4project.sem4.entity.Product;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDto extends BaseDto{
    private String content;
    private PostDto post;
    private UserDto user;
    private ProductDto product;
}

