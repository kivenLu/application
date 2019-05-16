package com.alibaba.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * iframe页面转发控制
 */
@Controller
public class PageController {

    @RequestMapping("/welcome")
    public String toWelcome() {
        return "/pages/welcome.html";
    }

    @RequestMapping("/my-questionnaire")
    public String toList() {
        return "/pages/questionnaire/list.html";
    }

    @RequestMapping("/my-add")
    public String toAdd() {
        return "/pages/questionnaire/add.html";
    }

    @RequestMapping("/user")
    public String index () {
        return "/pages/user/user.html";
    }
    @RequestMapping("/wjmm")
    public String toWjmm() {
        return "/pages/wjmm/wjmm.html";
    }
}
