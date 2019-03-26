package com.privilege.controller;

import com.privilege.po.Privileges;
import com.privilege.po.Roles;
import com.privilege.service.PrivilegeService;
import com.privilege.service.impl.PrivilegeServiceImpl;
import com.privilege.service.RolesService;
import com.privilege.service.impl.RolesServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/RolesServlet")
public class RolesServlet extends HttpServlet {
    private RolesService rolesService = new RolesServiceImpl();
    private PrivilegeService privilegeService = new PrivilegeServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取
        String choose = request.getParameter("choose");
        switch (choose){
            case "1":
                selectRolesList(request,response);
                break;
            case "2":
                selectPrivilege(request,response);
                break;
            case "3":
                addRoles(request,response);
                break;
            default:
                System.out.println("操作有误");
                 break;
        }

    }


    private void addRoles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        //调方法
        Roles roles = new Roles(1, name, description);
        int rows = rolesService.addRoles(roles);
        if(rows>0){
            request.getRequestDispatcher("RolesServlet?choose=1").forward(request,response);
        }
    }

    //调用查询权限的方法
    private void selectPrivilege(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收角色名称
        String rolename = request.getParameter("rolename");
        int roleid = Integer.parseInt(request.getParameter("roleid"));
        //根据roleid查询已经具备权限的方法
        List<Privileges> privilegeList = privilegeService.getPrivilegeList(roleid);
        List<Privileges> privilegesList = privilegeService.getPrivilegesList(roleid);
        HttpSession session = request.getSession();
        //存
        session.setAttribute("roleid",roleid);
        session.setAttribute("rolename",rolename);
        session.setAttribute("privilegeList",privilegeList);
        session.setAttribute("privilegesList",privilegesList);
        //转
        request.getRequestDispatcher("/jsp/grant_privilege.jsp").forward(request,response);
    }

    private void selectRolesList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //调
        List<Roles> rolesList = rolesService.selectRoles();
        //存
        HttpSession session = request.getSession();
        session.setAttribute("rolesList",rolesList);
        //转
        request.getRequestDispatcher("/jsp/roles_list.jsp").forward(request,response);
    }
}
