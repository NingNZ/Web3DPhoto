package com.example.last.service;

import com.example.last.entity.User;
import com.example.last.util.Result;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User checkLog(User user);
    Result register(User user);
}
