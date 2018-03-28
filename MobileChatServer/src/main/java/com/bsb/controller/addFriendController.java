package com.bsb.controller;

import com.bsb.model.UserModifyModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "addFriendController", urlPatterns = "/addFriend")
public class addFriendController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModifyModel userModifyModel = new UserModifyModel();

        try {
            userModifyModel.addFriend(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
