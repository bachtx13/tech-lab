package com.sem4project.sem4.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Invoice extends BaseEntity{
    private String fullName;
    private String email;
    private String phone;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "invoice_product", joinColumns = @JoinColumn(name = "invoice_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
    @ManyToOne(cascade = CascadeType.ALL)
    private Province province;
    @ManyToOne(cascade = CascadeType.ALL)
    private District district;
    private String address;
    private String note;
    @OneToOne(cascade = CascadeType.ALL)
    private Transactional transactional;
}
