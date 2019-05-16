package com.alibaba.application.controller;

import com.alibaba.application.entity.User;
import com.alibaba.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WJmmController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/wjmm/selectUser")
    public User selectUser(String name) {
        User u = new User();
        u.setName(name);
        User user = userService.selectUserByName(u);
        System.out.println(user);
        return  user;
    }

    @ResponseBody
    @RequestMapping("/wjmm/update-password")
    public String update(Integer id, String password) {
        try {
            User u = new User();
            u.setId(id);
            u.setPassword(password);
            userService.updatePasswordById(u);
        }catch (Exception e){
            return com.alibaba.application.entity.ResponseBody.FAIL;
        }

        return  com.alibaba.application.entity.ResponseBody.SUCCESS;
    }

}
