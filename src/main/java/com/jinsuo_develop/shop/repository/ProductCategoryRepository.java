package com.jinsuo_develop.shop.repository;

import com.jinsuo_develop.shop.domain.Product;
import com.jinsuo_develop.shop.domain.ProductCategory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    List<ProductCategory> findByProduct(Product product);

    @Override
    @EntityGraph(attributePaths = {"category"})
    Optional<ProductCategory> findById(Long id);
}