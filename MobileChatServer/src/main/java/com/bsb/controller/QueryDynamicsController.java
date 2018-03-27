package com.bsb.controller;

import com.bsb.model.UserDynamicModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "QueryDynamicsController", urlPatterns = "/queryDynamics")
public class QueryDynamicsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDynamicModel userDynamicModel = new UserDynamicModel();
        try {
            userDynamicModel.queryDynamic(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
