package com.sem4project.sem4.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class FilterOption extends BaseEntity{
    private String name;
    private String rule;
    @OneToOne(cascade = CascadeType.ALL)
    private FilterProperty filterProperty;
}
