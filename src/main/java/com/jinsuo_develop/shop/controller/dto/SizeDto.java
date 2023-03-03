package com.jinsuo_develop.shop.controller.dto;

import com.jinsuo_develop.shop.domain.clothes.SizeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SizeDto {
    private String sizeType;

    public SizeDto(SizeType sizeType) {
        this.sizeType = sizeType.name();
    }
}
