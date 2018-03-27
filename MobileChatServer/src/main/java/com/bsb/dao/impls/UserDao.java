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

    private static final String DEFAULT_IMAGE = "default.jpg";

    private BasicDataSource basicDataSource = DBCPUtil.getBasicDataSource();

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
    public boolean login(String username, String password) throws SQLException {

        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;


        SQL = "SELECT id,username,email,user_image FROM user_table WHERE username = ? and password = ?";

        connection = basicDataSource.getConnection();
        preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return true;
        }

        DBCPUtil.closeResources(connection, preparedStatement, resultSet);
        return false;
    }


    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
//     * @param email 邮箱
     * @return true false
     * @throws SQLException
     */
    @Override
    public boolean register(String username, String password)  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        SQL = "INSERT INTO user_table VALUES (NULL, ?, ?, ?, ?)";

        try {
            connection = basicDataSource.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, username + "@qq.com");
            preparedStatement.setString(4, DEFAULT_IMAGE);

            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                DBCPUtil.closeResources(connection, preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean updateUser(String username) throws SQLException {
        //todo 用户更新信息
        return true;
    }

    @Override
    public boolean uploadImage(String username, String imageUrl) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        SQL = "UPDATE user_table SET user_image = ? WHERE username = ?";

        connection = basicDataSource.getConnection();
        preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, imageUrl);
        preparedStatement.setString(2, username);

        if (preparedStatement.executeUpdate() != 0) {
            DBCPUtil.closeResources(connection, preparedStatement);
            return true;
        }

        DBCPUtil.closeResources(connection, preparedStatement);
        return false;
    }

    @Override
    public String queryUserImage(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String url = null;

        SQL = "SELECT user_image FROM user_table WHERE username = ?";
        connection = basicDataSource.getConnection();
        preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, username);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            url = resultSet.getString("user_image");
        }

        DBCPUtil.closeResources(connection, preparedStatement, resultSet);
        return url;
    }

    @Override
    public User queryUser(String username) throws SQLException {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        SQL = "SELECT email, user_image FROM user_table WHERE username = ?";

        connection = basicDataSource.getConnection();
        preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, username);

        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println("has rec");
            User user = new User();
            user.setUsername(username);
            user.setEmail(resultSet.getString("email"));
            user.setImageUrl(resultSet.getString("user_image"));
            DBCPUtil.closeResources(connection, preparedStatement, resultSet);
            return user;
        }

        DBCPUtil.closeResources(connection, preparedStatement, resultSet);
        return null;
    }

    @Override
    public List<User> queryAllFriends(String username) throws SQLException {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        User user = null;
        SQL = "SELECT friendname FROM user_friend_table WHERE username = ?";

        List<User> allFriends = new ArrayList<>();

        connection = basicDataSource.getConnection();
        preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, username);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            user = queryUser(resultSet.getString("friendname"));
            if (user != null) {
                allFriends.add(user);
            }
        }

        DBCPUtil.closeResources(connection, preparedStatement, resultSet);
        return allFriends;
    }

}
