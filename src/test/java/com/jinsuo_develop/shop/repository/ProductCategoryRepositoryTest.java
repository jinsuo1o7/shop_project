package com.jinsuo_develop.shop.repository;

import com.jinsuo_develop.shop.domain.Category;
import com.jinsuo_develop.shop.domain.Product;
import com.jinsuo_develop.shop.domain.ProductCategory;
import com.jinsuo_develop.shop.domain.clothes.Clothes;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@SpringBootTest
class ProductCategoryRepositoryTest {
    @Autowired ProductCategoryRepository productCategoryRepository;
    @Autowired CategoryRepository categoryRepository;
    @Autowired ProductRepository productRepository;
    @Autowired EntityManager em;

    @Test
    void test() {
        Category category = new Category();
        category.setName("ca");
        categoryRepository.save(category);

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategory(category);
        productCategoryRepository.save(productCategory);

        em.flush();
        em.clear();

//        productCategoryRepository.findById(category.getId());
        log.info("{}","----------------------------------------");
        ProductCategory find = productCategoryRepository.findById(productCategory.getId()).get();
        String name = find.getCategory().getName();
        log.info("{}",name);
    }

    @Test
    void protest() {
        Category category = new Category();
        category.setName("ca");
        categoryRepository.save(category);

        Product product = new Clothes();
        product.setName("clothes");
        productRepository.save(product);

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategory(category);
        productCategory.setProduct(product);
        productCategoryRepository.save(productCategory);

        em.flush();
        em.clear();

        Product findProduct = productRepository.findById(product.getId()).get();
        List<String> collect = findProduct.getProductCategories().stream()
                .map(pro -> pro.getCategory().getName()).collect(Collectors.toList());
        for (String s : collect) {
            log.info("{}",s);
        }
    }
}