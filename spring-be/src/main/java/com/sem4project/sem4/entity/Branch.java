package com.sem4project.sem4.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Branch extends BaseEntity {
    private String name;
    @ManyToOne
    private Province province;
    @ManyToOne
    private District district;
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<BranchProduct> branchProducts;
}
