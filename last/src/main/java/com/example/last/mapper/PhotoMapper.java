package com.example.last.mapper;

import com.example.last.entity.Photo;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface PhotoMapper {
    @Select("select * from photoset order by id asc")
    List<Photo> FindAll();
    @Insert("insert into photoset values (#{id}, #{address})")
    void insert(int id,String address);
    @Update("update photoset set id=#{newid},address=#{address} where id=#{oldid}")
    void update(int newid , String address, int oldid);
    @Delete("delete from photoset where id=#{id}")
    void delete(int id);
}
