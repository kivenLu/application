package com.alibaba.application.controller;

import com.alibaba.application.entity.ResponseBody;
import com.alibaba.application.entity.User;
import com.alibaba.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 注册控制器
 */
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String toRegister() {
        return "register.html";
    }

    @org.springframework.web.bind.annotation.ResponseBody
    @RequestMapping("/registerUser")
    public String index(User user, HttpServletRequest request) {
        User userByName = userService.selectUserByName(user);
        if(null == userByName) {
            user.setPower(1);
            System.out.println(user);
            try {
                userService.insert(user);
            }catch (Exception e) {
                e.printStackTrace();
                return ResponseBody.FAIL;
            }
            System.out.println("当前注册用户：" + user);
            User name = userService.selectUserByName(user);
            request.getSession().setAttribute("user",name);
            return ResponseBody.SUCCESS;
        }else  {
            return ResponseBody.FAIL;
        }
    }
}
