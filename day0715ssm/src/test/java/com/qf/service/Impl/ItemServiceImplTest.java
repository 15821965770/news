package com.qf.service.Impl;

import com.qf.mapper.AcTest;
import com.qf.pojo.Item;
import com.qf.service.ItemService;
import com.qf.utils.PageInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

public class ItemServiceImplTest extends AcTest {
    @Autowired
    ItemService itemService;

    @Test
    public void findByNameAndLimit() {
        PageInfo<Item> byNameAndLimit = itemService.findByNameAndLimit(null, 1, 10);


    }

    @Test
    public void testsave(){

//        (name,price,production_date,description,pic)
        Item item=new Item();
        item.setName("李四");
        item.setPrice(new BigDecimal(20));
        item.setProductionDate(new Date());
        item.setDescription("dddddd");
        item.setPic("qqqqqqqqqq");
        Integer count = itemService.save(item);
//        assertEquals(new Integer("1"),count);
        System.out.println(count);
    }

    @Test
    public void del() {
        Integer del = itemService.del(21L);
        System.out.println(del);
    }
}