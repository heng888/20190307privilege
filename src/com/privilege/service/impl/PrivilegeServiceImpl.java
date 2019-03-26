package com.privilege.service.impl;

import com.privilege.dao.PrivilegesDao;
import com.privilege.dao.impl.PrivilegesDaoImpl;
import com.privilege.po.Privileges;
import com.privilege.service.PrivilegeService;

import java.util.List;

public class PrivilegeServiceImpl implements PrivilegeService {
    private PrivilegesDao privilegesDao = new PrivilegesDaoImpl();
    @Override
    public List<Privileges> getPrivilegeList(int roleid) {
        return  privilegesDao.getPrivilegeList(roleid);
    }

    @Override
    public List<Privileges> getPrivilegesList(int roleid) {
        return privilegesDao.getPrivilegesList(roleid);
    }

    @Override
    public List<Privileges> selectAllPrivilege() {
        return privilegesDao.selectAllPrivilege();
    }

    @Override
    public int toPrivilegeDel(int roleid, String[] strs) {
        return privilegesDao.toPrivilegeDel(roleid,strs);
    }

    @Override
    public int toPrivilegeAdd(int roleid, String[] strs) {
        return privilegesDao.toPrivilegeAdd(roleid,strs);
    }

    @Override
    public int toPrivilege(Privileges privilege) {
        return privilegesDao.toPrivilege(privilege);
    }


}
