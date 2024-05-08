package com.example.last.controller;

import com.example.last.entity.User;
import com.example.last.service.UserService;
import com.example.last.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result login(User user){
        User ExistOne=null;
        ExistOne=userService.checkLog(user);
//        System.out.println(ExistOne);
        if(ExistOne!=null){
            if(ExistOne.getPassword().equals(user.getPassword())){
                return Result.Success("Right",ExistOne.getAccess());
            }else{
                return Result.Failure("密码错误");
            }
        }else{
            return Result.Failure("用户尚未注册");
        }

    }
    @PostMapping("/register")
    public Result register(User user){
        User ExistOne=null;
        ExistOne=userService.checkLog(user);
        if(ExistOne==null){
            return userService.register(user);
        }else{
            return Result.Failure("用户"+user.getUsername()+"已被注册");
        }
    }
}
