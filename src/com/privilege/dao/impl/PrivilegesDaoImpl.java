package com.privilege.dao.impl;

import com.privilege.dao.PrivilegesDao;
import com.privilege.po.Privileges;
import com.privilege.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class PrivilegesDaoImpl implements PrivilegesDao {
    private QueryRunner queryRunner = C3P0Util.getQueryRunner();

    @Override
    public List<Privileges> getPrivilegeList(int roleid) {
        String sql="select * from \"privileges\" where \"id\" in (select \"privilege_id\" from \"roleprivilege\" where \"role_id\" =?))";
        List<Privileges> privilegeList =null;
        try {
            privilegeList = queryRunner.query(sql, new BeanListHandler<Privileges>(Privileges.class),roleid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return privilegeList;
    }

    @Override
    public List<Privileges> getPrivilegesList(int roleid) {
        String sql="select * from \"privileges\" where \"id\" not in(select \"privilege_id\" from \"roleprivilege\" where \"role_id\" =?))";
        List<Privileges> privilegesList= null;
        try {
            privilegesList = queryRunner.query(sql, new BeanListHandler<Privileges>(Privileges.class),roleid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return privilegesList;
    }



    @Override
    public int toPrivilegeDel(int roleid, String[] strs) {
        String sql="delete  from \"roleprivilege\" where \"privilege_id\"=? and \"role_id\"="+roleid;
        int rows=0;
        for(int i=0;i<strs.length;i++){
            try {
                rows+= queryRunner.update(sql, strs[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return rows;
    }

    @Override
    public int toPrivilegeAdd(int roleid, String[] strs) {
        String sql="insert into \"roleprivilege\" values(?,?)";
        int addid=0;
        for(int i=0;i<strs.length;i++){
            try {
                addid+= queryRunner.update(sql, strs[i],roleid);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return  addid;
    }

    @Override
    public int toPrivilege(Privileges privilege) {
        String sql = "insert into \"privileges\" values(PRIVILEGESid.nextval,?,?,?)";
        int rows=0;
        try {
            rows = queryRunner.update(sql, privilege.getName(), privilege.getFnpath(), privilege.getDescription());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public List<Privileges> selectAllPrivilege() {
        String sql="select * from \"privileges\"";
        List<Privileges> privilegesList=null;
        try {
           privilegesList = queryRunner.query(sql, new BeanListHandler<Privileges>(Privileges.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return privilegesList;
    }

}
