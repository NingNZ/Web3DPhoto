package com.example.last.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String password;
    private char access;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public char getAccess() {
        return access;
    }
}

