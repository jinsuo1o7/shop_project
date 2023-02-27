package com.jinsuo_develop.shop.repository;

import com.jinsuo_develop.shop.domain.clothes.Size;
import com.jinsuo_develop.shop.domain.clothes.SizeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SizeRepository extends JpaRepository<Size,Long> {
    Optional<Size> findBySizeType(SizeType sizeType);
}
