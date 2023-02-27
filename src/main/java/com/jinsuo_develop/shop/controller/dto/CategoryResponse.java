package com.jinsuo_develop.shop.controller.dto;

import com.jinsuo_develop.shop.domain.ProductCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CategoryResponse {
    private final List<String> categories = new ArrayList<>();

    public CategoryResponse(List<ProductCategory> productCategories) {
        for (ProductCategory category : productCategories) {
            categories.add(category.getCategory().getName());
        }
    }
}
