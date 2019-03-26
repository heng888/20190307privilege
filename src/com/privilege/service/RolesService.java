package com.privilege.service;

import com.privilege.po.Roles;

import java.util.List;

public interface RolesService {
    //查询角色
    List<Roles> selectRoles();

    //增加角色
    int addRoles(Roles roles);
}
