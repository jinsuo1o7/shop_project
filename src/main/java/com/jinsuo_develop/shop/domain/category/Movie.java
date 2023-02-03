package com.jinsuo_develop.shop.domain.category;

import com.jinsuo_develop.shop.domain.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter @Setter
public class Movie extends Item {
    private String director;
    private String actor;
}
