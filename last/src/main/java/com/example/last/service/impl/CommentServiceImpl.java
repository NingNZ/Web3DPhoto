package com.example.last.service.impl;

import com.example.last.entity.Comments;
import com.example.last.mapper.CommentsMapper;
import com.example.last.service.CommentService;
import com.example.last.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentsMapper commentsMapper;
    @Override
    public List<Comments> Findall() {
        return commentsMapper.FindAll();
    }

    @Override
    public Result addComment(Comments comm) {
        commentsMapper.addComment(comm.getCid(),comm.getUsername(),comm.getContent());
        return Result.OK("提交成功");
    }
}
