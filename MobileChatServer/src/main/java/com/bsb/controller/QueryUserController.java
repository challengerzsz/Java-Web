package com.bsb.controller;

import com.bsb.model.UserQueryModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "QueryUserController", urlPatterns = "/queryUser")
public class QueryUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        UserQueryModel userQueryModel = new UserQueryModel();
        try {
            userQueryModel.queryUser(username, req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
