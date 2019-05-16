package com.alibaba.application.controller;

import com.alibaba.application.entity.PageInfo;
import com.alibaba.application.entity.ResponseBody;
import com.alibaba.application.entity.User;
import com.alibaba.application.entity.UserVo;
import com.alibaba.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户管理
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     * @return
     */
    @org.springframework.web.bind.annotation.ResponseBody
    @RequestMapping("/user-list")
    public UserVo selectUserList(Integer page, Integer limit,String name) {
        PageInfo p = new PageInfo();
        p.setLimit(limit);
        p.setName(name);
        System.out.println("那么 :" + name);
        p.setPage(((page) - 1) * limit);
        List<User> list = userService.selectUserList(p);
        UserVo vo = new UserVo();
        vo.setCode(0);
        vo.setCount(userService.selectUserCount(p));
        vo.setData(list);
        vo.setMsg("查询成功");
        return vo;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @org.springframework.web.bind.annotation.ResponseBody
    @RequestMapping("/del-user/{id}")
    public String delSurvey(@PathVariable(name = "id")Integer id) {
        if (null != id) {
            try{
                userService.delUserById(id);
                return ResponseBody.SUCCESS;
            }catch (Exception e) {
                System.out.println("当前id为空！");
                e.printStackTrace();
                return ResponseBody.FAIL;
            }
        }else {
            return ResponseBody.FAIL;
        }
    }

    /**
     * 编辑用户
     * @param id
     * @return
     */
    @RequestMapping("/edit-user/{id}")
    public String editUser(@PathVariable(name = "id")Integer id, HttpServletRequest request) {
        if (null != id) {
            try{
                User user = userService.selectUserByID(id);
                request.setAttribute("editUser",user);
                return "/pages/user/edit.html";
            }catch (Exception e) {
                System.out.println("当前id为空！");
                e.printStackTrace();
                return null;
            }
        }else {
            return null;
        }
    }

    /**
     * 保存用户
     * @param name
     * @param password
     * @return
     */
    @org.springframework.web.bind.annotation.ResponseBody
    @RequestMapping("/edit-user-now")
    public String saveUser(String name,String password) {
        System.out.println(name + ", " + password);
        User user = new User();
        user.setPassword(password);
        user.setName(name);
        try {
            userService.updateUserByName(user);
            return ResponseBody.SUCCESS;
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.FAIL;
        }
    }

}
