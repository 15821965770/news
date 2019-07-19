package com.qf.service.Impl;

import com.qf.mapper.UserMapper;
import com.qf.pojo.User;
import com.qf.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public Integer checkUsername(String username) {


        Integer count =userMapper.checkUsername(username);
        return count;

    }

    @Override
    public Integer register(User user) {

     String newpwd=new Md5Hash(user.getPassword(),null,1024).toString();
     user.setPassword(newpwd);

       Integer userCount= userMapper.register(user);
        return userCount;
    }

    @Override
    public User login(String username, String password) {

        User user=userMapper.checklogin(username);
        if (user!=null){
            String newpwdlogin=new Md5Hash(password,null,1024).toString();
            if (user.getPassword().equals(newpwdlogin)){
                return user;
            }
        }
        return null;
    }
}
