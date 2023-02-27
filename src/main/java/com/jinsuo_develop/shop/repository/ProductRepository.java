package com.jinsuo_develop.shop.repository;

import com.jinsuo_develop.shop.domain.Product;
import com.jinsuo_develop.shop.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product, Long> {
}
