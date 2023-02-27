package com.jinsuo_develop.shop.domain.clothes;

import com.jinsuo_develop.shop.domain.Product;
import lombok.Getter;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Clothes extends Product {
    @OneToMany(mappedBy = "clothes")
    private List<ClothesSize> clothesSizes = new ArrayList<>();
}