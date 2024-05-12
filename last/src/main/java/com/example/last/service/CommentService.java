package com.example.last.service;

import com.example.last.entity.Comments;
import com.example.last.util.Result;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CommentService {
    List<Comments> Findall();
    Result addComment(Comments comm);
}
