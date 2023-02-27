package com.jinsuo_develop.shop.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateProductCategoryRequest {
    private List<String> categories;
}
