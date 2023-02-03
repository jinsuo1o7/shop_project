package com.jinsuo_develop.shop.service;

import com.jinsuo_develop.shop.domain.Item;
import com.jinsuo_develop.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository repository;

    @Transactional
    public Long saveItem(Item item) {
        repository.save(item);
        return item.getId();
    }

    public List<Item> findItems() {
        return repository.findAll();
    }

    public Item findOne(Long itemId) {
        return repository.findOne(itemId);
    }
}
