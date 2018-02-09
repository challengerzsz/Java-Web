package com.bsb.login.servlet;

import com.bsb.dao.UserDao;
import com.bsb.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("GBK");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //获取UserDao实例
        UserDao userDao = new UserDao();

        User user = userDao.login(username, password);
        if (user != null) {
            //转发到LoginSuccess.jsp
            //getRequestDispatcher()请求转发
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
            System.out.println("login successfully");
        } else {
            request.getRequestDispatcher("LoginFailed.jsp").forward(request, response);
            System.out.println("login failed");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
