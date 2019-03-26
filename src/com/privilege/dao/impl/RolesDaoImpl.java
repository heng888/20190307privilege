package com.privilege.dao.impl;

import com.privilege.dao.RolesDao;
import com.privilege.po.Roles;
import com.privilege.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class RolesDaoImpl implements RolesDao {
    private QueryRunner queryRunner = C3P0Util.getQueryRunner();
    @Override
    public List<Roles> selectRoles() {
        String sql = "select * from \"roles\"";
        List<Roles> rolesList = null;
        try {
            rolesList = queryRunner.query(sql, new BeanListHandler<Roles>(Roles.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rolesList;
    }

    @Override
    public int addRoles(Roles roles) {
        String sql="insert into roles values(null,?,?)";
        int rows =0;
        try {
            rows = queryRunner.update(sql, roles.getName(), roles.getDescription());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }
}
