package com.jinsuo_develop.shop.repository;

import com.jinsuo_develop.shop.domain.Product;
import com.jinsuo_develop.shop.domain.clothes.Clothes;
import com.jinsuo_develop.shop.domain.clothes.ClothesSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

public interface ClothesSizeRepository extends JpaRepository<ClothesSize, Long> {
    List<ClothesSize> findByClothesId(Long ClothesId);

    void deleteByClothesId(Long id);

    void deleteByClothes(Clothes clothes);

}
