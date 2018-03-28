package com.bsb.model;

import com.bsb.service.impls.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UserModifyModel {
    private UserService userService = new UserService();

    public UserModifyModel() throws IOException {
    }

    public void addFriend(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        userService.addFriend(request, response);
    }
}
