package com.jinsuo_develop.shop.controller;

import com.jinsuo_develop.shop.controller.dto.*;
import com.jinsuo_develop.shop.domain.Product;
import com.jinsuo_develop.shop.domain.clothes.Clothes;
import com.jinsuo_develop.shop.domain.clothes.SizeType;
import com.jinsuo_develop.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.jinsuo_develop.shop.domain.clothes.Clothes.createClothes;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/new")
    @CrossOrigin(origins = "http://localhost:3000", methods = RequestMethod.POST)
    public OkResponse addProduct(@Valid @RequestBody AddProductRequest request) {
        log.info("{}", request);
        Product product = null;
        Long id = null;

        if (request.getType() == ProductType.CLOTHES) {
            product = createClothes(request);
            id = productService.saveClothesProduct((Clothes) product, request.getCategories());

            for (SizeAndStock sizeAndStock : request.getSizeAndStocks()) {
                Integer quantity = sizeAndStock.getStockQuantity();
                if(quantity != null) {
                    SizeType sizeType = SizeType.valueOf(sizeAndStock.getSizeType().toUpperCase());
                    productService.saveClothesSizeAndStock((Clothes) product, sizeType, quantity);
                }
            }
        }
        return new OkResponse("product added!" + id);
    }

    @GetMapping
    public Page<ProductsResponse> getProducts(@PageableDefault(size = 12, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return productService.findAllProduct(pageable)
                .map(ProductsResponse::new);
    }

    @GetMapping("/{id}")
    public ProductDetailResponse findProductDetail(@PathVariable Long id) {
        return productService.findProductCategory(id);
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

    @PatchMapping("/{id}/sizes")
    public OkResponse updateSize(@PathVariable Long id, @Valid @RequestBody List<SizeAndStock> sizeAndStock) {
        productService.updateSizeAndStock(id, sizeAndStock);
        return new OkResponse("ok");
    }

    @DeleteMapping("{id}")
    public OkResponse deleteProduct(@PathVariable Long id) {
        log.info("delete : {}", id);
        productService.deleteProduct(id);
        return new OkResponse("ok");
    }

}
