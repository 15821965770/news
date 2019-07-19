package com.qf.service.Impl;

import com.qf.mapper.ItemMapper;
import com.qf.pojo.Item;
import com.qf.service.ItemService;
import com.qf.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    final
    ItemMapper itemMapper;

    @Autowired
    public ItemServiceImpl(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }


    @Override
    @Transactional
    public Integer save(Item item) {

        return itemMapper.save(item);
    }

    @Override
    public PageInfo<Item> findByNameAndLimit(String name, Integer page, Integer size) {


        Long total=itemMapper.findCountByName(name);

        System.out.println(total);

        PageInfo<Item> pageInfo=new PageInfo<>(page, size, total);
        System.out.println(pageInfo);

        List<Item> list= itemMapper.findByNameAndLimit(name,pageInfo.getOffset(),pageInfo.getSize());
        pageInfo.setList(list);
        System.out.println(pageInfo);

        return pageInfo;
    }

    @Override
    public Integer del(Long id) {

        return itemMapper.del(id);
    }
}
