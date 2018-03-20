package com.bsb.controller;

import com.bsb.model.UserModel;
import com.bsb.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User();

        UserModel userModel = new UserModel();
        try {
            user = userModel.login(userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (user == null) {
            req.getRequestDispatcher("/failed.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/success.jsp").forward(req, resp);
        }

    }
}
