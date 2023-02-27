package com.jinsuo_develop.shop.controller.dto;

import com.jinsuo_develop.shop.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ProductsResponse {
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private Long id;
    private String imageUrl;
    private String name;
    private int price;

    public ProductsResponse(Product product) {
        this.createdDate = product.getCreatedDate();
        this.lastModifiedDate = product.getLastModifiedDate();
        this.id = product.getId();
        this.imageUrl = product.getImageUrl();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}