package com.jinsuo_develop.shop.controller;

import com.jinsuo_develop.shop.controller.dto.*;
import com.jinsuo_develop.shop.domain.Product;
import com.jinsuo_develop.shop.domain.clothes.Clothes;
import com.jinsuo_develop.shop.domain.clothes.SizeType;
import com.jinsuo_develop.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/new")
    public OkResponse addProduct(@Valid @RequestBody AddProductRequest request) {
        Product product = null;
        Long id = null;

        if (request.getType() == ProductType.CLOTHES) {
            product = createClothes(request);
            id = productService.saveClothesProduct((Clothes) product, request.getCategories());

            for (SizeAndStock sizeAndStock : request.getSizeAndStocks()) {
                SizeType sizeType = SizeType.valueOf(sizeAndStock.getSizeType().toUpperCase());
                int quantity = sizeAndStock.getStockQuantity();
                productService.saveClothesSizeAndStock((Clothes) product, sizeType, quantity);
            }
        }
        return new OkResponse("product added!" + id);
    }

    @GetMapping
    public List<ProductsResponse> getProducts() {
        List<Product> allProduct = productService.findAllProduct();
        return allProduct.stream().map(ProductsResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDetailResponse getProductDetail(@PathVariable Long id) {
        Product product = productService.findOneProduct(id);
        return new ProductDetailResponse((Clothes) product);
    }

    @PatchMapping("/{id}")
    public OkResponse updateProduct(@PathVariable Long id, @Valid @RequestBody UpdateProductRequest request) {
        productService.updateProduct(id, request);
        return new OkResponse("ok");
    }

    @PatchMapping("/{id}/categories")
    public OkResponse updateCategory(@PathVariable Long id, @Valid @RequestBody UpdateProductCategoryRequest request) {
        productService.updateProductCategory(id, request);
        return new OkResponse("ok");
    }

    private static Product createClothes(AddProductRequest request) {
        Clothes clothes = new Clothes();
        clothes.setImageUrl(request.getImageUrl());
        clothes.setName(request.getName());
        clothes.setDescription(request.getDescription());
        clothes.setPrice(request.getPrice());
        return clothes;
    }
}
