package org.example.springboot1.controller;


import org.example.springboot1.pojo.User;
import org.example.springboot1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("/list")
    public List<User> list() throws Exception {

        List<User> userList = userService.findAll();


        //返回数据(json)
        return userList;
    }
}
