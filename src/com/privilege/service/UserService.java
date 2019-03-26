package com.privilege.service;

import com.privilege.po.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    //用户登陆
    int login(String username,String password) throws SQLException;

    //添加用户
    int addUser(User user);

    //查询用户
    List<User> selectAllUser();

    //修改密码
    int updatePassword(String username,String password);

    int selectUser(String username);
}
