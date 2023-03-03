package com.jinsuo_develop.shop.repository;

import com.jinsuo_develop.shop.domain.Product;
import com.jinsuo_develop.shop.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    List<ProductCategory> findByProductId(Long id);
}