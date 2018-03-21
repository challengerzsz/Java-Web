package com.bsb.service;

import com.bsb.pojo.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IUserService {

    User login(String username, String password) throws IOException, SQLException;

    boolean register(String username, String password, String confirmPassword, String email) throws SQLException;

    boolean updateUser(int id);

    User queryUser(int id);

    List<User> queryAllUsers();
}
