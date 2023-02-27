package com.jinsuo_develop.shop.domain.clothes;

import com.jinsuo_develop.shop.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Size {
    @Id @GeneratedValue
    @Column(name = "size_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private SizeType sizeType;

    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClothesSize> sizeList = new ArrayList<>();
}
