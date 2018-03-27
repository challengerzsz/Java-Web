package com.bsb.model;

import com.bsb.service.impls.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserLogoutModel {
    UserService userService = new UserService();

    public UserLogoutModel() throws IOException {
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.logout(request, response);
    }
}
