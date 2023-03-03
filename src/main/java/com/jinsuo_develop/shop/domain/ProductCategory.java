package com.jinsuo_develop.shop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public void changeProduct(Product product) {
        this.product = product;
        product.getProductCategories().add(this);
    }

    public void changeCategory(Category category) {
        this.category = category;
        category.getItemCategories().add(this);
    }

    public ProductCategory(Product product, Category category) {
        this.product = product;
        this.category = category;
    }

    // 생성 메서드
    public static ProductCategory createProductCategory(Product product, Category category) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.changeCategory(category);
        productCategory.changeProduct(product);
        return productCategory;
    }
}
