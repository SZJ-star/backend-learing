package org.emample;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class JdbcTest {


    @Test
    public void testUpdate() throws Exception {
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取数据库连接
        String url = "jdbc:mysql://59.110.90.98:3306/ssmy?serverTimezone=Asia/Shanghai&useSSL=false";
        String username = "root";
        String password = "617620";
        Connection connection= DriverManager.getConnection(url,username,password);
        //获取SQL语句执行对象
        Statement statement = connection.createStatement();
        //执行SQL
        int i=statement.executeUpdate("update `user` set age =25 where id=1");
        System.out.println(i);
        //释放资源
        statement.close();
        connection.close();

    }


}
