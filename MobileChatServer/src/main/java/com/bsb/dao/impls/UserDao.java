package com.bsb.dao.impls;

import com.bsb.dao.IUserDao;
import com.bsb.pojo.Dynamic;
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

    private static final String DEFAULT_IMAGE_URL = "http://120.79.196.225:8080/MobileChatServer/image/userdefault.jpg";
//    private static final String TMP_USER_IMAGE = "用户头像URL";

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
            preparedStatement.setString(4, DEFAULT_IMAGE_URL);

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
    public boolean updateDynamic(String username, String dynamic, String imageUrl) throws SQLException {

        boolean result = false;

        SQL = "INSERT INTO user_journal VALUES (NULL, ?, ?, ?, NULL)";

        Connection connection = basicDataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, dynamic);
        preparedStatement.setString(3, imageUrl);


        if (preparedStatement.executeUpdate() != 0) {
            result = true;
        }

        DBCPUtil.closeResources(connection, preparedStatement);

        return result;
    }

    @Override
    public boolean checkIsFriend(String username, String friendname) throws SQLException {

        SQL = "SELECT username, friendname FROM user_friend_table WHERE username = ?";

        Connection connection = basicDataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();

        boolean result = false;

        while (resultSet.next()) {
            if (resultSet.getString("username").equals(username)
                    && resultSet.getString("friendname").equals(friendname)) {
                result = true;
//                System.out.println("加过好友");
                break;
            }
        }
//        System.out.println("没加过");
        DBCPUtil.closeResources(connection, preparedStatement, resultSet);
        return result;
    }

    @Override
    public boolean addFriend(String username, String friendname) throws SQLException {

        SQL = "INSERT INTO user_friend_table VALUES (null, ?, ?)";

        Connection connection = basicDataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, friendname);

        int resultCode = preparedStatement.executeUpdate();
        boolean result = false;

        if (resultCode != 0) {
            result = true;
        }

        DBCPUtil.closeResources(connection, preparedStatement);
        return result;
    }

    @Override
    public List<Dynamic> queryDynamic(String username, int startIndex, int endIndex) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Dynamic> dynamics = new ArrayList<>();

        SQL = "select DISTINCT total.username, total.user_image, journal.content, journal.image, update_time  from (select usr.username, usr.user_image from user_table usr,\n" +
                "(select friendname from user_friend_table where username = ?) friends where username = friends.friendname or usr.username = ?) total,\n" +
                "user_journal journal where total.username = journal.username ORDER BY journal.update_time DESC limit ?, ?";
        connection = basicDataSource.getConnection();
        preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, username);
        preparedStatement.setInt(3, startIndex);
        preparedStatement.setInt(4, endIndex);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Dynamic dynamic = new Dynamic();
            dynamic.setUsername(resultSet.getString("username"));
            dynamic.setUserImageUrl(resultSet.getString("user_image"));
            dynamic.setContentText(resultSet.getString("content"));
            dynamic.setContentImageUrl(resultSet.getString("image"));
            dynamic.setUpdateDate(resultSet.getString("update_time"));

            dynamics.add(dynamic);
        }

        DBCPUtil.closeResources(connection, preparedStatement, resultSet);
        return dynamics;
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
