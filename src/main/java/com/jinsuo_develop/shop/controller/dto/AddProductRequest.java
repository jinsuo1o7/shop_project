package com.jinsuo_develop.shop.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Getter
@NoArgsConstructor
public class AddProductRequest {
    @Enumerated(EnumType.STRING)
    private ProductType type;
    private String imageUrl;
    private String name;
    private int price;
    private String description;
    private List<String> categories;
    private List<SizeAndStock> sizeAndStocks;
}
