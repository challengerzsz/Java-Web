package com.bsb.dao.impls;

import com.bsb.dao.IUserDao;
import com.bsb.pojo.User;
import com.bsb.utils.DBUtil.DBCPUtil;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao implements IUserDao{

    private BasicDataSource basicDataSource = DBCPUtil.getBasicDataSource();
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String SQL =null;

    public UserDao() throws IOException {
    }

    @Override
    public User login(String username, String password) throws SQLException {
        User user = new User();

        SQL = "SELECT id,username,email,user_image FROM user_table WHERE username = ? and password = ?";
        connection = basicDataSource.getConnection();
        preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            return null;
        } else {
            user.setId(resultSet.getInt(1));
            user.setUsername(resultSet.getString(2));
            user.setEmail(resultSet.getString(3));
        }

        DBCPUtil.closeResources(connection, preparedStatement, resultSet);
        return user;
    }

    @Override
    public User register(String username, String password, String confirmedPassword) {
        return null;
    }

    @Override
    public boolean updateUser(int id) {
        return false;
    }

    @Override
    public User queryUser(int id) {
        return null;
    }

    @Override
    public List<User> queryAllUsers() {
        return null;
    }
}
