package com.qf.service;

import com.qf.pojo.Item;
import com.qf.utils.PageInfo;

public interface ItemService {
    public Integer save(Item item) ;

    PageInfo<Item> findByNameAndLimit(String name, Integer page, Integer size);

    Integer del(Long id);
}
