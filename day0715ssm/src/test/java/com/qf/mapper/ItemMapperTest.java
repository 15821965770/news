package com.qf.mapper;

import com.qf.pojo.Item;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemMapperTest extends AcTest {
    @Autowired
    ItemMapper itemMapper;

    @Test
    public void findCountByName() {
        Long countByName = itemMapper.findCountByName(null);
        System.out.println(countByName);

    }

    @Test
    public void findByNameAndLimit() {
        List<Item> list = itemMapper.findByNameAndLimit(null, 1, 5);
        for (Item item : list) {
            System.out.println(item);
        }

    }

    @Test
    public void testdel(){
        Integer del = itemMapper.del(20L);
        System.out.println(del);
    }
}