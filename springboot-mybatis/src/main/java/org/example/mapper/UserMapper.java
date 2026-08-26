package org.example.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user")
    public List<User> findAll();
}
