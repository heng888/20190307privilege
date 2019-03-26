package com.privilege.service;

import com.privilege.po.Privileges;

import java.util.List;

public interface PrivilegeService {
    //查询
    List<Privileges> getPrivilegeList(int id);
    List<Privileges> getPrivilegesList(int id);
    List<Privileges> selectAllPrivilege();
    //授权
    int toPrivilegeDel(int roleid,String[] strs);
    int toPrivilegeAdd(int roleid,String[] strs);

    //增加权限
    int toPrivilege(Privileges privilege);
}
