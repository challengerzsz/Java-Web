package com.bsb.model;

import com.bsb.pojo.User;
import com.bsb.service.impls.UserService;

import java.io.IOException;
import java.sql.SQLException;

public class UserModel {
    public UserService userService = new UserService();

    public UserModel() throws IOException {
    }

    public User login(String username, String password) throws SQLException {
        return userService.login(username, password);
    }

}
