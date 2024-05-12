package com.example.last.service;

import com.example.last.util.Range;
import com.example.last.util.Result;

public interface PhotoService {
    Range Countall();
    Result insert(int num);
    Result delete(int num);
    void Modify(int newid,int oldid);
}
