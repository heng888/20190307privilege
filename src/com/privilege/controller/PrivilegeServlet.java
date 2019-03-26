package com.privilege.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.privilege.po.Privileges;
import com.privilege.service.PrivilegeService;
import com.privilege.service.impl.PrivilegeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/PrivilegeServlet")
public class PrivilegeServlet extends HttpServlet {
    private PrivilegeService privilegeService = new PrivilegeServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String choose = request.getParameter("choose");
        switch (choose){
            case "1":
                toPrivilege(request,response);
                break;
            case "2":
                addPrivilege(request,response);
             break;
            case "3":
                selectAllPrivilege(request,response);
                break;
             default:
                 System.out.println("操作失误");
                 break;
        }

    }

    private void selectAllPrivilege(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调方法
        List<Privileges> privilegesList = privilegeService.selectAllPrivilege();
        //存
        HttpSession session = request.getSession();
        session.setAttribute("privilegesList",privilegesList);
        //转
        request.getRequestDispatcher("/jsp/privilege_list.jsp").forward(request,response);
    }

    private void addPrivilege(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取参数
        String p_name = request.getParameter("p_name");
        String p_url = request.getParameter("p_url");
        String p_description = request.getParameter("p_description");
        //调方法
        Privileges privileges = new Privileges(1,p_name,p_url,p_description);
        int rows = privilegeService.toPrivilege(privileges);
        if(rows>0){
            request.getRequestDispatcher("PrivilegeServlet?choose=3").forward(request,response);
        }
    }

    private void toPrivilege(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //取参数
        int roleid = Integer.parseInt(request.getParameter("roleid"));
        String[] privilegedeleteids = request.getParameter("privilegedeleteid").split(",");
        String[] privilegeaddids = request.getParameter("privilegeaddid").split(",");
        //调方法
        int delrows = privilegeService.toPrivilegeDel(roleid, privilegedeleteids);
        int addrows = privilegeService.toPrivilegeAdd(roleid, privilegeaddids);
        if(delrows>0 && addrows>0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result",1);
            String jsonString = JSON.toJSONString(jsonObject);
            PrintWriter writer = response.getWriter();
            writer.write(jsonString);
            writer.flush();
            writer.close();
        }
    }


}
