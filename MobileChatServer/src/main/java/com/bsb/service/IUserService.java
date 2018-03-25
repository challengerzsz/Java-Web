package com.bsb.service;

import com.bsb.pojo.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IUserService {

    boolean login(String username, String password) throws IOException, SQLException;

    boolean register(String username, String password, String confirmPassword,
                     String email, String image) throws SQLException;

    boolean updateUser(int id);

    User queryUser(String username);

    List<User> queryAllUsers();
}
