package com.jinsuo_develop.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class ProductCategory {
    @Id @GeneratedValue
    @Column(name = "product_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public void setProduct(Product product) {
        this.product = product;
        product.getProductCategories().add(this);
    }

    public void setCategory(Category category) {
        this.category = category;
        category.getItemCategories().add(this);
    }

    // 생성 메서드
    public static ProductCategory createProductCategory(Category category) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategory(category);
        return productCategory;
    }
}
