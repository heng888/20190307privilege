package com.privilege.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.privilege.po.User;
import com.privilege.service.UserService;
import com.privilege.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choose = request.getParameter("choose");
        switch (choose){
            case "1":
                try {
                    login(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "2":
                addUser(request,response);
                break;
            case "3":
                selectAllUser(request,response);
                break;
            case "4":
                try {
                    updatePassword(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "5":
                selectUser(request,response);
                break;
            case "6":
                selectUser(request,response);
                break;
            case "7":
                toUserRole(request,response);
                break;
            default:
                System.out.println("操作错误");
        }
    }

    private void toUserRole(HttpServletRequest request, HttpServletResponse response) {

    }

    //查询用户名是否已经存在
    private void selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //取参数
        String username = request.getParameter("username");
        //调方法
        int i = userService.selectUser(username);
        PrintWriter writer = response.getWriter();
        if(i>0){
            writer.write("用户名已经存在");
        }else {
            writer.write("用户名可用");
        }
        writer.flush();
        writer.close();
    }

    /**
     * 修改密码
     */
    private void updatePassword(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        //接收参数
        String username = request.getParameter("username");
        String oldpassword = request.getParameter("oldpassword");
        String password = request.getParameter("password");

        int rows = userService.login(username, oldpassword);
        if(rows>0) {
                int i = userService.updatePassword(username, password);
                if(i>0){
                    request.getRequestDispatcher("login.jsp").forward(request,response);
                }else{
                    request.getRequestDispatcher("/UserServlet?choose=4").forward(request,response);
                }
        }
    }

    /**
     * 查询所有用户
     */
    private void selectAllUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调方法
        List<User> userList = userService.selectAllUser();
        //存
        HttpSession session = request.getSession();
        session.setAttribute("userList",userList);
        //转
        request.getRequestDispatcher("/jsp/users_list.jsp").forward(request,response);
    }

    /**
     * 添加用户
     */
    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");

        //调方法
        User user = new User(1, username, password, nickname);
        int rows = userService.addUser(user);
        if(rows>0){
            request.getRequestDispatcher("/UserServlet?choose=3").forward(request,response);
        }else{
            response.sendRedirect("/jsp/users_add.jsp");
        }
    }

    /**
     * 验证用户名跟密码
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        //取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int rows = userService.login(username,password);
        if(rows>0){
            //存用户名
            HttpSession session = request.getSession();
            session.setAttribute("username",username);
            //转页面
            request.getRequestDispatcher("/jsp/welcome.jsp").forward(request,response);
        }else{
            response.sendRedirect("/login.jsp");
        }
    }
}
