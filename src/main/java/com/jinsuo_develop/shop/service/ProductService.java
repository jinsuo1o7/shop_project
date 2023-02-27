package com.jinsuo_develop.shop.service;

import com.jinsuo_develop.shop.controller.dto.UpdateProductCategoryRequest;
import com.jinsuo_develop.shop.controller.dto.UpdateProductRequest;
import com.jinsuo_develop.shop.domain.Category;
import com.jinsuo_develop.shop.domain.Product;
import com.jinsuo_develop.shop.domain.ProductCategory;
import com.jinsuo_develop.shop.domain.clothes.Clothes;
import com.jinsuo_develop.shop.domain.clothes.ClothesSize;
import com.jinsuo_develop.shop.domain.clothes.Size;
import com.jinsuo_develop.shop.domain.clothes.SizeType;
import com.jinsuo_develop.shop.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.jinsuo_develop.shop.domain.ProductCategory.*;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SizeRepository sizeRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ClothesSizeRepository clothesSizeRepository;

    @Transactional
    public Long saveClothesProduct(Clothes clothes, List<String> categories) {
        productRepository.save(clothes);
        setClothesCategories(clothes, categories);
        return clothes.getId();
    }

    @Transactional
    public void saveClothesSizeAndStock(Clothes clothes, SizeType sizeType, int quantity) {
        Size size = sizeRepository.findBySizeType(sizeType).orElseThrow();
        ClothesSize clothesSize = ClothesSize.createClothesSize(clothes, size, quantity);
        clothesSizeRepository.save(clothesSize);
    }

    private void setClothesCategories(Clothes clothes, List<String> categories) {
        for (String name : categories) {
            Category category = categoryRepository.findByName(name).orElseThrow();
            ProductCategory productCategory = createProductCategory(category);
            productCategory.setProduct(clothes);
            productCategoryRepository.save(productCategory);
        }
    }

    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    public Product findOneProduct(Long id) {
        return productRepository.findById(id).get();
    }

    @Transactional
    public void updateProduct(Long id, UpdateProductRequest request) {
        Product product =  productRepository.findById(id).orElseThrow();
        product.setImageUrl(request.getImageUrl());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
    }

    @Transactional
    public void updateProductCategory(Long id, UpdateProductCategoryRequest request) {
        Product product = productRepository.findById(id).orElseThrow();
        List<ProductCategory> productCategories = productCategoryRepository.findByProduct(product);
        List<String> categories = request.getCategories();
        List<Category> categoryList = new ArrayList<>();

        for (String name : categories) {
            Category category = categoryRepository.findByName(name).orElseThrow();
            categoryList.add(category);
        }

        for (ProductCategory productCategory : productCategories) {
            if (!categoryList.contains(productCategory.getCategory())) {
                productCategoryRepository.delete(productCategory);
            }
        }

        for (Category category : categoryList) {
            if (productCategories.stream().noneMatch(pc -> pc.getCategory().equals(category))) {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setProduct(product);
                productCategory.setCategory(category);
                productCategoryRepository.save(productCategory);
            }
        }
    }
}
