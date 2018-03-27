package com.bsb.model;

import com.bsb.service.impls.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UserQueryModel {
    public UserService userService = new UserService();

    public UserQueryModel() throws IOException {
    }

    public void queryUser(String username, HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        userService.queryUser(username, request, response);
    }


}
