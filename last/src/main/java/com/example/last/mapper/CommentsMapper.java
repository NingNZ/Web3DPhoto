package com.example.last.mapper;

import com.example.last.entity.Comments;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentsMapper {
    @Select("select * from comments ")
    List<Comments> FindAll();
    @Insert("insert into comments values (#{cid},#{username},#{content})")
    void addComment(String cid,String username,String content);
}
