package com.jinsuo_develop.shop.domain;

import com.jinsuo_develop.shop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@Getter @Setter
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemCategory> itemCategories = new ArrayList<>();

    private String name;
    private int price;
    private int stockQuantity;

    // biz logic
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
