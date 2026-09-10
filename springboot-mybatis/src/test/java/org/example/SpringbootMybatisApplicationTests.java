package org.example;

import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootMybatisApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Test
    public void testFindAll() {
        List<User> userList = userMapper.findAll();
        userList.forEach(System.out::println);
    }
    @Test
    public void testDeleteById() {
        userMapper.deleteById(9);
    }

    @Test
    public void testInsert(){
        User user=new User(9,"aaa","777","111",18);
        userMapper.insert(user);
    }

    @Test
    public void testUpdate(){
        User user=new User(1,"aaa","777","112",18);
        userMapper.update(user);
    }

    @Test
    public void testSelect(){
        User user=userMapper.findByUsernameAndPassword("xiaoqiao","123456");
        System.out.println(user);
    }
}
