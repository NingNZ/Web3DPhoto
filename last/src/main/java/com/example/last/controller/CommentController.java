package com.example.last.controller;

import com.example.last.entity.Comments;
import com.example.last.service.CommentService;
import com.example.last.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/list")
    public List<Comments> FindAll(){
        return commentService.Findall();
    }
    @PostMapping("/add")
    public Result addComment(Comments comm){
        return commentService.addComment(comm);
    }
}
