package com.qf.mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserMapperTest extends AcTest {
    @Autowired
    UserMapper userMapper;
    @Test
    public void checkUsername() {

        Integer count = userMapper.checkUsername("admin");
        System.out.println(count);
    }
}