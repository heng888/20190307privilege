package com.privilege.service.impl;

import com.privilege.dao.RolesDao;
import com.privilege.dao.impl.RolesDaoImpl;
import com.privilege.po.Roles;
import com.privilege.service.RolesService;

import java.util.List;

public class RolesServiceImpl implements RolesService {
    private RolesDao rolesDao = new RolesDaoImpl();
    @Override
    public List<Roles> selectRoles() {
       return rolesDao.selectRoles();
    }

    @Override
    public int addRoles(Roles roles) {
        return rolesDao.addRoles(roles);
    }

}
