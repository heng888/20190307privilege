package com.privilege.service.impl;

import com.privilege.dao.UserDao;
import com.privilege.dao.impl.UserDaoImpl;
import com.privilege.po.User;
import com.privilege.service.UserService;
import com.privilege.util.MD5Util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public int login(String username, String password){
        //MD5加密
        password = MD5Util.MD5Encoding(password);
        //调用dao层login方法
        int rows=0;
        try {
           rows = userDao.login(username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    /**
     * 添加用户
     */
    @Override
    public int addUser(User user) {
        //MD5加密
        user.setPassword(MD5Util.MD5Encoding(user.getUsername()));
        return userDao.addUser(user);
    }

    /**
     * 查询所有用户
     */
    @Override
    public List<User> selectAllUser() {
        return userDao.selectAllUser();
    }

    /**
     * 更改密码
     */
    @Override
    public int updatePassword(String username, String password) {
        password=MD5Util.MD5Encoding(password);
        return userDao.updatePassword(username,password);
    }

    /**
     * 添加用户时查询用户是否已经存在
     */
    @Override
    public int selectUser(String username) {
        return userDao.selectUser(username);
    }
}
