package com.bsb.dao;

import com.bsb.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    User login(String username, String password) throws SQLException;

    boolean register(String username, String password, String confirmedPassword, String image) throws SQLException;

    boolean updateUser(String username) throws SQLException;

    User queryUser(String username) throws SQLException;

    List<User> queryAllUsers() throws SQLException;
}
