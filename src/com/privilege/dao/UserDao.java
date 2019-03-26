package com.privilege.dao;

import com.privilege.po.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    //用户登陆
    int login(String username,String password) throws SQLException;

    //添加用户
    int addUser(User user);

    //查询用户信息
    List<User> selectAllUser();

    //修改密码
    int updatePassword(String username, String password);

    //查询用户名
    int selectUser(String username);
}
