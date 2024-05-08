package com.example.last.mapper;

import com.example.last.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from userall where username=#{username}")
    User SelectUser(String username, String password);
    @Insert("insert into userall values (#{username},#{password},'c')")
    void register(String username,String password);
}
