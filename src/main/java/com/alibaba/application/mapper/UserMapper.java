package com.alibaba.application.mapper;

import com.alibaba.application.entity.PageInfo;
import com.alibaba.application.entity.User;

import java.util.List;

public interface UserMapper {
    void insert(User user);
    User selectUserByName(User user);
    List<User> selectUserList(PageInfo p);
    void delUserById(Integer id);
    int selectUserCount(PageInfo p);
    User selectUserByID(Integer id);
    void updateUserByName(User user);
    void updatePasswordById(User user);

}
