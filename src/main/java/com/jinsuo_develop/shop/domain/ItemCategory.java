package com.jinsuo_develop.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ItemCategory {
    @Id @GeneratedValue
    @Column(name = "item_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public void setItem(Item item) {
        this.item = item;
        item.getItemCategories().add(this);
    }

    public void setCategory(Category category) {
        this.category = category;
        category.getItemCategories().add(this);
    }
}
