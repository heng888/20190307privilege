package com.privilege.dao;

import com.privilege.po.Roles;

import java.util.List;

public interface RolesDao {
    //查询角色
    List<Roles> selectRoles();

    //增加角色
    int addRoles(Roles roles);


}
