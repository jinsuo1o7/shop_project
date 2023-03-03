package com.jinsuo_develop.shop.domain.clothes;

import com.jinsuo_develop.shop.controller.dto.AddProductRequest;
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
    public static Product createClothes(AddProductRequest request) {
        Clothes clothes = new Clothes();
        clothes.setImageUrl(request.getImageUrl());
        clothes.setName(request.getName());
        clothes.setDescription(request.getDescription());
        clothes.setPrice(request.getPrice());
        return clothes;
    }
}