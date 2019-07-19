package com.qf.controller;

import com.qf.pojo.User;
import com.qf.service.UserService;
import com.qf.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.qf.constant.SsmConstant.*;

/**
 * 用户模块的controller层
 * create by 郑大仙丶
 * 2019/7/15 10:27
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private  UserService userService;
//    @Autowired
//    private SendSMSUtil sendSMS;



    // 跳转到注册页面
    @GetMapping("/register-ui")
    public String registerUI(){
        // 转发到注册页面.
        return "user/register";
    }

    @PostMapping("/check-username")
    @ResponseBody
    public ResultVo checkUsername(@RequestBody User user) {
        Integer count =userService.checkUsername(user.getUsername());
        return new ResultVo(0,"成功",count);
    }


//    @PostMapping(value = "/send-sms",produces = "text/html;charset=utf-8")
//    @ResponseBody
//    public String sendSMS(@RequestParam String phone ,  HttpSession session){
//       String result= sendSMS.sendSMS(session,phone);
//       return result;
//    }

//    /register
    @PostMapping("/register")
    public String register(@Valid User user, BindingResult bindingResult, String registerCode,
                           RedirectAttributes redirectAttributes,
                           HttpSession session){
        String attribute="888888";
        //校验验证码
        if (!StringUtils.isEmpty(registerCode)){
//            String attribute = (String) session.getAttribute(CODE);
            if (!registerCode.equals(attribute)){
                redirectAttributes.addAttribute("registerInfo", "验证码输入错误");
                return REDIRECT+REGISTER_UI;
            }
        }

            //校验参数

            if (bindingResult.hasErrors()){
                String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
                redirectAttributes.addAttribute("registerInfo", defaultMessage);
                return REDIRECT+REGISTER_UI;
            }

            //调用servie

           Integer count= userService.register(user);
            if (count==1){
                return REDIRECT+LOGIN_UI;
            }else {
                redirectAttributes.addAttribute("registerInfo", "用户注册失败 请联系管理员");
                return REDIRECT+REGISTER_UI;
            }

        }

        @GetMapping("/login-ui")
    public String loginUI(){

        return "/user/login";
        }

//    http://localhost/user/login

        @PostMapping("/login")
        @ResponseBody
    public ResultVo login(String username,String password,HttpSession session){
        if (StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            return new ResultVo(1,"用户名或密码错误",null);
        }
        User user=userService.login(username,password);
        if (user!=null){
            session.setAttribute("user", user);
            return new ResultVo(0,"登录成功",null);
        }else {
            return new ResultVo(1,"登录失败",null);
        }

        }


}



