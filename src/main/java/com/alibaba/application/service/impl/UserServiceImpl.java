package com.alibaba.application.service.impl;

import com.alibaba.application.entity.PageInfo;
import com.alibaba.application.entity.User;
import com.alibaba.application.mapper.UserMapper;
import com.alibaba.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public void insert(User user) {
        userMapper.insert(user);
    }

    public User selectUserByName(User user) {
        return userMapper.selectUserByName(user);
    }

    @Override
    public List<User> selectUserList(PageInfo p) {
        return userMapper.selectUserList(p);
    }

    @Override
    public void delUserById(Integer id) {
        userMapper.delUserById(id);
    }

    @Override
    public int selectUserCount(PageInfo p) {
        return userMapper.selectUserCount(p);
    }

    @Override
    public User selectUserByID(Integer id) {
        return userMapper.selectUserByID(id);
    }

    @Override
    public void updateUserByName(User user) {
        userMapper.updateUserByName(user);
    }

    @Override
    public void updatePasswordById(User user) {
        userMapper.updatePasswordById(user);
    }
}
