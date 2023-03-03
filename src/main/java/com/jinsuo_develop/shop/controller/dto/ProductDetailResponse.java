package com.jinsuo_develop.shop.controller.dto;

import com.jinsuo_develop.shop.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@NoArgsConstructor
public class ProductDetailResponse {
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private Long id;
    private String imageUrl;
    private String name;
    private int price;

    private String description;
    private List<String> categories = new ArrayList<>();
    private List<SizeAndStock> sizeAndStocks = new ArrayList<>();

    public ProductDetailResponse(Product product) {
        this.createdDate = product.getCreatedDate();
        this.lastModifiedDate = product.getLastModifiedDate();
        this.id = product.getId();
        this.imageUrl = product.getImageUrl();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }
}
