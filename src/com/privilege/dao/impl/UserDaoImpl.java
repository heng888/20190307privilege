package com.privilege.dao.impl;

import com.privilege.dao.UserDao;
import com.privilege.po.User;
import com.privilege.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    //获取C3P0的操作对象
    private QueryRunner queryRunner = C3P0Util.getQueryRunner();

    /**
     *查询用户名跟密码
     */
    @Override
    public int login(String username, String password){
        String sql="select count(*) from \"users\" where \"username\"=? and \"password\"=?";
        String count=null;
       try {
          count= queryRunner.query(sql, new ScalarHandler(1), username, password).toString();
       } catch (SQLException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(count);
    }

    /**
     *新增用户
     */
    @Override
    public int addUser(User user) {
        String sql = "insert into \"users\" values(usersid.nextval,?,?,?)";
        int rows=0;
        try {
            rows = queryRunner.update(sql, user.getUsername(), user.getPassword(), user.getNickname());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> selectAllUser() {
        String sql="select * from \"users\"";
        List<User> userList = null;
        try {
            userList = queryRunner.query(sql, new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * 更改密码
     * @return
     */
    @Override
    public int updatePassword(String username, String password) {
        String sql="update \"users\" set \"password\"=? where \"username\"=?";
        int rows=0;
        try {
            rows = queryRunner.update(sql, password,username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    /**
     * 添加用户时查询用户是否已经存在
     */
    @Override
    public int selectUser(String username) {
        String sql="select count(\"id\") from \"users\" where \"username\"=?";
        Long rows=0L;
        try {
            rows = (Long) queryRunner.query(sql,new ScalarHandler(),username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows.intValue();
    }
}
