package com.sem4project.sem4.dto.dtomodel;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class BaseDto {
    private UUID id;
    private boolean disable;
    private Instant createdAt;
    private UserDto createdBy;
    private Instant updatedAt;
    private UserDto updatedBy;
}
