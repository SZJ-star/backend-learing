package org.example.springboot1.service.impl;

import org.example.springboot1.dao.UserDao;
import org.example.springboot1.dao.impl.UserDaoImpl;
import org.example.springboot1.pojo.User;
import org.example.springboot1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


//@Component
@Service
public class UserServiceImpl implements UserService {

    @Autowired //应用程序运行时，会自动查询该类型的bean对象，并赋值给该成员变量
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        List<String> lines = userDao.findAll();

        //解析用户信息，封装为User对象->list集合
        List<User> userList = lines.stream().map(line -> {
            String[] parts = line.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];
            String name = parts[3];
            Integer age = Integer.parseInt(parts[4]);
            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new User(id, username, password, name, age, updateTime);
        }).toList();

        return userList;
    }
}
