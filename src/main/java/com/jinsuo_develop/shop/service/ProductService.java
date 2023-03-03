package com.jinsuo_develop.shop.service;

import com.jinsuo_develop.shop.controller.dto.ProductDetailResponse;
import com.jinsuo_develop.shop.controller.dto.SizeAndStock;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.jinsuo_develop.shop.domain.ProductCategory.*;
import static com.jinsuo_develop.shop.domain.clothes.ClothesSize.*;

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
        ClothesSize clothesSize = createClothesSize(clothes, size, quantity);
        clothesSizeRepository.save(clothesSize);
    }

    private void setClothesCategories(Clothes clothes, List<String> categories) {
        for (String name : categories) {
            Category category = categoryRepository.findByName(name).orElseThrow();
            ProductCategory productCategory = createProductCategory(clothes, category);
            productCategoryRepository.save(productCategory);
        }
    }

    public Page<Product> findAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product findOneProduct(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public ProductDetailResponse findProductCategory(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        ProductDetailResponse response = new ProductDetailResponse(product);

        List<ProductCategory> productCategories = productCategoryRepository.findByProductId(productId);
        List<ClothesSize> clothesSizes = clothesSizeRepository.findByClothesId(productId);

        for (ProductCategory pc : productCategories) {
            response.getCategories().add(pc.getCategory().getName());
        }
        for (ClothesSize cz : clothesSizes) {
            int stockQuantity = cz.getStockQuantity();
            String size = cz.getSize().getSizeType().toString();
            response.getSizeAndStocks().add(new SizeAndStock(size, stockQuantity));
        }

        return response;
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
        List<ProductCategory> productCategories = productCategoryRepository.findByProductId(id);
        List<Category> categoryList = new ArrayList<>();

        for (String name : request.getCategories()) {
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
                ProductCategory productCategory = new ProductCategory(product, category);
                productCategoryRepository.save(productCategory);
            }
        }
    }

    @Transactional
    public void updateSizeAndStock(Long id, List<SizeAndStock> sizeAndStock) {
        Product product = productRepository.findById(id).orElseThrow();

        // Delete all existing ClothesSize entities for the product
        clothesSizeRepository.deleteByClothesId(id);

        for (SizeAndStock sa : sizeAndStock) {
            SizeType st = SizeType.valueOf(sa.getSizeType().toUpperCase());
            Size size = sizeRepository.findBySizeType(st).orElseThrow();
            ClothesSize newClothesSize = createClothesSize((Clothes) product, size, sa.getStockQuantity());
            clothesSizeRepository.save(newClothesSize);
        }
    }

    @Transactional
    public void deleteProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Clothes clothes = (Clothes) productOptional.get();
            clothesSizeRepository.deleteByClothes(clothes); // Delete related records in clothes_size table
            productRepository.delete(clothes); // Delete the product
        }
    }
}
