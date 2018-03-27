package com.bsb.dao;

import com.bsb.pojo.Dynamic;
import com.bsb.pojo.User;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    boolean login(String username, String password) throws SQLException;

    boolean register(String username, String password) throws SQLException;

    boolean updateUser(String username) throws SQLException;

    boolean uploadImage(String username, String imageUrl) throws SQLException;

    boolean updateDynamic(String username, String dynamic, String imageUrl) throws SQLException;

    List<Dynamic> queryDynamic(String username, int startIndex, int endIndex) throws SQLException;

    String queryUserImage(String username) throws SQLException;

    User queryUser(String username) throws SQLException;

    List<User> queryAllFriends(String user) throws SQLException;


}
