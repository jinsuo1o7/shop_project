package com.jinsuo_develop.shop.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Data
public class TestBody {
    private List<SizeAndStock> sizeAndStockList;
}
