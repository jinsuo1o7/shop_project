package com.jinsuo_develop.shop.domain.clothes;

import com.jinsuo_develop.shop.exception.NotEnoughStockException;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ClothesSize {
    @Id @GeneratedValue
    @Column(name = "clothes_size_id")
    private Long id;
    private int stockQuantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id")
    private Size size;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clothes_id")
    private Clothes clothes;

    public static ClothesSize createClothesSize(Clothes clothes, Size size, int stockQuantity) {
        ClothesSize clothesSize = new ClothesSize();
        clothesSize.changeClothes(clothes);
        clothesSize.changeSize(size);
        clothesSize.changeQuantity(stockQuantity);
        return clothesSize;
    }

    public void changeQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void changeClothes(Clothes clothes) {
        this.clothes = clothes;
        clothes.getClothesSizes().add(this);
    }

    public void changeSize(Size size) {
        this.size = size;
        size.getSizeList().add(this);
    }

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restQuantity = this.stockQuantity - quantity;
        if(restQuantity < 0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restQuantity;
    }
}
