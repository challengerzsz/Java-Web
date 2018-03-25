package com.bsb.model;

import com.bsb.pojo.User;
import com.bsb.service.impls.UserService;

import java.io.IOException;
import java.sql.SQLException;

public class UserLoginModel {
    public UserService userService = new UserService();

    public UserLoginModel() throws IOException {
    }

    public boolean login(String username, String password) throws SQLException {
        return userService.login(username, password);
    }

}
