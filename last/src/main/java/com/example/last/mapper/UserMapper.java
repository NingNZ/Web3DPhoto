package com.example.last.mapper;

import com.example.last.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from userall where username=#{username}")
    User SelectUser(String username, String password);
    @Insert("insert into userall values (#{username},#{password},'c')")
    void register(String username,String password);
    @Update("update userall set password=#{password} where username=#{username}")
    void changePassword (String username,String password);
}
