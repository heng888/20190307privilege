package com.privilege;

import com.privilege.po.User;
import com.privilege.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        System.out.println(queryRunner);
        String sql="select * from \"users\"";
        List<User> userList = null;
        try {
            userList = queryRunner.query(sql, new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(User user : userList){
            System.out.println(user);
        }
    }
}
