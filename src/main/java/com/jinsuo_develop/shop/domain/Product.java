package com.jinsuo_develop.shop.domain;

import com.jinsuo_develop.shop.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@Getter @Setter
public abstract class Product extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    private String imageUrl;
    private String name;
    private int price;
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductCategory> productCategories = new ArrayList<>();
}
