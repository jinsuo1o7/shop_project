package com.jinsuo_develop.shop.controller.dto;

import com.jinsuo_develop.shop.domain.clothes.Clothes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
@NoArgsConstructor
public class ProductDetailResponse {
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    private String imageUrl;
    private String name;
    private int price;
    private List<String> categories;
    private String description;

    private List<SizeAndStock> sizeAndStocks = new ArrayList<>();

    public ProductDetailResponse(Clothes product) {
        this.createdDate = product.getCreatedDate();
        this.lastModifiedDate = product.getLastModifiedDate();
        this.imageUrl = product.getImageUrl();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();

        this.categories = product.getProductCategories().stream()
                .map(p -> p.getCategory().getName())
                .collect(Collectors.toList());

        product.getClothesSizes().forEach(clothesSize -> {
            SizeAndStock sizeAndStock = new SizeAndStock(
                    clothesSize.getSize().getSizeType().toString(),
                    clothesSize.getStockQuantity()
            );
            sizeAndStocks.add(sizeAndStock);
        });
    }
}
