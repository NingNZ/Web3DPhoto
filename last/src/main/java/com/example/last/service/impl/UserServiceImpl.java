package com.example.last.service.impl;

import com.example.last.entity.User;
import com.example.last.mapper.UserMapper;
import com.example.last.service.UserService;
import com.example.last.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserMapper userMapper;
    @Override
    public User checkLog(User user) {
        System.out.println(user);
        return  userMapper.SelectUser(user.getUsername(), user.getPassword());

    }

    @Override
    public Result register(User user) {
        userMapper.register(user.getUsername(),user.getPassword());
        return Result.Success("注册成功",'c');
    }

    @Override
    public Result changePassword(User user) {
        userMapper.changePassword(user.getUsername(), user.getPassword());
        return Result.OK("修改密码成功");
    }
}
