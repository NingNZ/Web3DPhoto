package com.example.last.service.impl;

import com.example.last.entity.Photo;
import com.example.last.mapper.PhotoMapper;
import com.example.last.service.PhotoService;
import com.example.last.util.Range;
import com.example.last.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  PhotoServiceImpl implements PhotoService {
    @Autowired
    private PhotoMapper photoMapper;
    @Override
    public Range Countall() {
        List<Photo> photoList =photoMapper.FindAll();
        return new Range (photoList.get(0).getId(),photoList.get(photoList.size()-1).getId(),photoList.size());
    }

    @Override
    public Result insert(int num) {
        String address="D:\\code\\IdeaProject\\last\\src\\main\\resources\\static\\"+num+".jpg";
        photoMapper.insert(num,address);
        return Result.OK("图片上传成功");
    }

    @Override
    public Result delete(int num) {
        photoMapper.delete(num);
        return null;
    }

    @Override
    public void Modify(int newid, int oldid) {
        String address="D:\\code\\IdeaProject\\last\\src\\main\\resources\\static\\"+newid+".jpg";
        photoMapper.update(newid,address,oldid);
    }
}
