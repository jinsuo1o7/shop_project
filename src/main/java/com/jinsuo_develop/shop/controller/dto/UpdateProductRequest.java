package com.jinsuo_develop.shop.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateProductRequest {
    private String imageUrl;
    private String name;
    private int price;
    private String description;
}
