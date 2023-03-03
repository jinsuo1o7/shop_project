package com.jinsuo_develop.shop.controller;

import com.jinsuo_develop.shop.controller.dto.CategoryDto;
import com.jinsuo_develop.shop.controller.dto.SizeDto;
import com.jinsuo_develop.shop.domain.Category;
import com.jinsuo_develop.shop.repository.SizeRepository;
import com.jinsuo_develop.shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final SizeRepository sizeRepository;

    @GetMapping("/categories")
    public List<CategoryDto> categories() {
        return categoryService.getCategories().stream()
                .map(c -> new CategoryDto(c.getName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/sizes")
    public List<SizeDto> sizes() {
        return sizeRepository.findAll().stream()
                .map(s -> new SizeDto(s.getSizeType()))
                .collect(Collectors.toList());
    }
}
