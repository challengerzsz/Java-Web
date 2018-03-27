package com.bsb.dao;

import com.bsb.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    boolean login(String username, String password) throws SQLException;

    boolean register(String username, String password) throws SQLException;

    boolean updateUser(String username) throws SQLException;

    boolean uploadImage(String username, String imageUrl) throws SQLException;

    String queryUserImage(String username) throws SQLException;

    User queryUser(String username) throws SQLException;

    List<User> queryAllFriends(String user) throws SQLException;


}
