package com.qf.service.Impl;

import com.qf.mapper.AcTest;
import com.qf.pojo.User;
import com.qf.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class UserServiceImplTest extends AcTest {
    @Autowired
    UserService userService;
    @Test
    public void checkUsername() {
        Integer integer = userService.checkUsername("admin");
        System.out.println(integer);
    }

//
//    private Long id;
//    @NotBlank(message = "用户名不能为空")
//    private String username;
//    @NotBlank(message = "密码不能为空")
//    private String password;
//    @NotBlank(message = "电话不能为空")
//    private String phone;
//
//    private Date created;


    @Test
    public void registertest() {
        User user=new User(6L,"zhangsan","111","555",new Date());
        Integer register = userService.register(user);
        System.out.println(register);
    }
    @Test
    public void testlogin(){
        User admin = userService.login("dachui", "66");
        System.out.println(admin);
    }
}