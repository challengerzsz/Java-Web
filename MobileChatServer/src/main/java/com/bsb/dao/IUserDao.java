package com.bsb.dao;

import com.bsb.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    User login(String username, String password) throws SQLException;

    User register(String username, String password, String confirmedPassword);

    boolean updateUser(int id);

    User queryUser(int id);

    List<User> queryAllUsers();
}
