package org.example.mapper;


import org.apache.ibatis.annotations.*;
import org.example.pojo.User;

import java.util.List;

@Mapper
public interface UserMapper {

//    @Select("select id, username, password, name, age from user")
    public List<User> findAll();

    @Delete("delete from user where id=#{id}")
    public void deleteById(Integer id);

    @Insert("insert into user(id, username, password, name, age) values (#{id}, #{username}, #{password}, #{name}, #{age})")
    public void insert(User user);

    @Update("update user set username=#{username}, password=#{password}, name=#{name}, age=#{age} where id=#{id}")
    public void update(User user);

    @Select("select * from user where username=#{username} and password=#{password}")
    public User findByUsernameAndPassword(@Param("username")String username, @Param("password")String password);
}