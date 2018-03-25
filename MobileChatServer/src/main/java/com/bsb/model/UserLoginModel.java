package com.bsb.model;

import com.bsb.service.impls.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UserLoginModel {
    public UserService userService = new UserService();

    public UserLoginModel() throws IOException {
    }

    public void login(String username, String password, HttpServletRequest request,
                      HttpServletResponse response) throws SQLException, IOException, ServletException {
        userService.login(username, password, request, response);

    }

}
