package com.alibaba.application.controller;


import com.alibaba.application.entity.User;
import com.alibaba.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录控制器
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    /**
     * 跳转至登录页
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login.html";
    }

    /**
     * 登录操作
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public Object login(User user, HttpServletRequest request) {
        System.out.println("登录用户为：" +user);
        if (null == user.getName()) {
            return com.alibaba.application.entity.ResponseBody.NO;
        }
        User userByName = userService.selectUserByName(user);
        if (userByName != null) {
            if (userByName.getPassword().equals(user.getPassword())) {
                request.getSession().setAttribute("user",userByName);
                return com.alibaba.application.entity.ResponseBody.SUCCESS;
            }else {
                return com.alibaba.application.entity.ResponseBody.ERROR;
            }
        }
        return com.alibaba.application.entity.ResponseBody.FAIL;
    }

    /**
     * 跳转主页操作
     * @return
     */
    @RequestMapping("/index")
    public String toIndex() {
        return "index";
    }

    /**
     * 主页展示操作
     * @return
     */
    @RequestMapping("/show")
    public String show() {
        return "pages/welcome";
    }

    /**
     * 退出系统
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        try {
            response.sendRedirect("/toLogin");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "login.html";
    }
}
