package com.jinsuo_develop.shop.domain.category;

import com.jinsuo_develop.shop.domain.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

@Entity
@Getter @Setter
public class Album extends Item {
    private String artist;
    private String etc;
}
