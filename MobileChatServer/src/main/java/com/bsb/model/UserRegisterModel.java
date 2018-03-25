package com.bsb.model;

import com.bsb.service.impls.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UserRegisterModel {
    public UserService userService = new UserService();

    public UserRegisterModel() throws IOException {
    }

    public void register(String username, String password, String confirmPassword, String email,
                            HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        userService.register(username, password, confirmPassword, email, request, response);
//        return true;
    }
}
