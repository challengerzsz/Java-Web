package com.bsb.model;

import com.bsb.service.impls.UserService;

import java.io.IOException;
import java.sql.SQLException;

public class UserRegisterModel {
    public UserService userService = new UserService();

    public UserRegisterModel() throws IOException {
    }

    public boolean register(String username, String password, String confirmPassword, String email) throws SQLException {
        System.out.println("调用model");
        return userService.register(username, password, confirmPassword, email);
    }
}
