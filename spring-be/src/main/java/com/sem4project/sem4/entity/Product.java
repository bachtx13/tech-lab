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
public class Product extends BaseEntity{
    private String title;
    private String productCode;
    private String warrantyPeriod;
    private Double cost;
    private Double promotional;
    private boolean status;
    @OneToOne(cascade = CascadeType.ALL)
    private Photo thumbnail;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "product_photo", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "photo_id"))
    private List<Photo> photos;
    private String video;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "product_gift", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "gift_id"))
    private List<Gift> gifts;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "product_specification", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "specification_id"))
    private List<Specification> specifications;
    @OneToOne
    private Post post;
    @ManyToMany(mappedBy = "products", cascade = CascadeType.MERGE)
    private List<Category> categories;
    @ManyToOne(cascade = CascadeType.ALL)
    private Brand brand;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Rate> rates;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<BranchProduct> branchProducts;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
