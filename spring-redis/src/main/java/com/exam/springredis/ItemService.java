package com.exam.springredis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ItemService {

    @Cacheable(value = "itemCache")
    public List<Double> get(String id) {
        List<Double> itemList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            itemList.add(Math.random() * Integer.valueOf(id));
            log.info("added");
        }
        return itemList;
    }
}
