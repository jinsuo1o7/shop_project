package com.jinsuo_develop.shop.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SizeAndStock {
    private String sizeType;
    private Integer stockQuantity;
}
