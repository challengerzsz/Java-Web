package com.bsb.dao;

import com.bsb.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    User user = null;
    private String SQL = "";
    public User login(String username, String password) {
        SQL = "select * from login_info where username = ? and password = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBDao.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(SQL);
            //将用户名以及密码填写到SQL的问号处
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();//返回一个查询到的数据集

            if (resultSet.next()) {
                user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
            connection.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBDao.closeConnection(connection);
        }
        return user;
    }
}
