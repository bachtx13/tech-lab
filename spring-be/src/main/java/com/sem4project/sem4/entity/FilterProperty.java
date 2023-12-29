package com.sem4project.sem4.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
public class FilterProperty extends BaseEntity{
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    @OneToOne(mappedBy = "filterProperty", cascade = CascadeType.ALL)
    private FilterOption filterOption;
}
