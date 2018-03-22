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
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao{

    private BasicDataSource basicDataSource = DBCPUtil.getBasicDataSource();
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String SQL =null;

    public UserDao() throws IOException {
    }

    /**
     * 用户登陆dao
     * @param username 用户名
     * @param password 密码
     * @return User对象
     * @throws SQLException
     */
    @Override
    public User login(String username, String password) throws SQLException {

        SQL = "SELECT id,username,email,user_image FROM user_table WHERE username = ? and password = ?";
        connection = basicDataSource.getConnection();
        preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setUsername(resultSet.getString(2));
            user.setEmail(resultSet.getString(3));
        }

        DBCPUtil.closeResources(connection, preparedStatement, resultSet);
        return null;
    }


    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @param email 邮箱
     * @param image 用户头像
     * @return true false
     * @throws SQLException
     */
    @Override
    public boolean register(String username, String password, String email, String image) throws SQLException {

        SQL = "INSERT INTO user_table VALUES (NULL, ?, ?, ?, ?)";

        connection = basicDataSource.getConnection();
        preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, "jpg");

        if (preparedStatement.executeUpdate() != 0) {
            DBCPUtil.closeResources(connection, preparedStatement);
            return true;
        }
        DBCPUtil.closeResources(connection, preparedStatement);
        return false;
    }

    @Override
    public boolean updateUser(String username) throws SQLException {
        //todo 用户更新信息
        return true;
    }

    @Override
    public User queryUser(String username) throws SQLException {

        SQL = "SELECT email, user_image FROM user_table WHERE username = ?";

        connection = basicDataSource.getConnection();
        preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, username);

        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = new User();
            user.setUsername(username);
            user.setEmail(resultSet.getString("email"));
            user.setImage(resultSet.getString("user_image"));
            DBCPUtil.closeResources(connection, preparedStatement, resultSet);
            return user;
        }

        DBCPUtil.closeResources(connection, preparedStatement, resultSet);
        return null;
    }

    @Override
    public List<User> queryAllUsers() throws SQLException {
        SQL = "SELECT username FROM user_table";

        List<User> allaUsers = new ArrayList<>();

        connection = basicDataSource.getConnection();
        preparedStatement = connection.prepareStatement(SQL);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            User user = new User();
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setImage(resultSet.getString("user_image"));
            allaUsers.add(user);
        }

        DBCPUtil.closeResources(connection, preparedStatement, resultSet);
        return allaUsers;
    }
}
