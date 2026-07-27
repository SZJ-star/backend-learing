package org.example.springboot1.dao.impl;

import cn.hutool.core.io.IoUtil;
import org.example.springboot1.dao.UserDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//@Component //将当前类交给IOC容器管理
@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public List<String> findAll(){
        //加载并读取user.txt文件，获取用户信息
        InputStream in=this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines= IoUtil.readLines(in, StandardCharsets.UTF_8,new ArrayList<>());
        return lines;
    }

}
