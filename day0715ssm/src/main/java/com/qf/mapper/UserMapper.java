package com.qf.mapper;

import com.qf.pojo.User;

public interface UserMapper {
    public Integer checkUsername(String username);

    Integer register(User user);

    User checklogin(String username);
}
