package com.bsb.controller;

import com.bsb.model.UserLoginModel;
import com.bsb.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,resp);
//        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        UserLoginModel userLoginModel = new UserLoginModel();
        try {
            if (userLoginModel.login(userName, password) == true) {
                req.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/jsp/failed.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
