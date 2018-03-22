package com.bsb.utils.DBUtil;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPUtil {
    private static BasicDataSource basicDataSource = null;

    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    private static int initialSize;
    private static int maxActive;
    private static int minIdle;


    public static Properties properties = new Properties();

    //静态代码块初始化properties对象并且对静态类变量赋值
    static {
        try {
            properties.load(DBCPUtil.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = properties.getProperty("driverClassName");
        url = properties.getProperty("url");
        user = properties.getProperty("username");
        password = properties.getProperty("password");
        initialSize = Integer.parseInt(properties.getProperty("initialSize"));
        maxActive = Integer.parseInt(properties.getProperty("maxActive"));
        minIdle = Integer.parseInt(properties.getProperty("minIdle"));
    }

    private DBCPUtil() {}

    //单例模式
    public static BasicDataSource getBasicDataSource() throws IOException {
        synchronized (DBCPUtil.class) {
            if (basicDataSource != null) {
                return basicDataSource;
            }

            basicDataSource = new BasicDataSource();

            basicDataSource.setDriverClassName(driver);
            basicDataSource.setUrl(url);
            basicDataSource.setUsername(user);
            basicDataSource.setPassword(password);
            basicDataSource.setInitialSize(initialSize);
            basicDataSource.setMaxActive(maxActive);
            basicDataSource.setMinIdle(minIdle);
        }
        return basicDataSource;
    }

//    public static Connection getConnection() throws SQLException {
//        return basicDataSource.getConnection();
//    }
//
//
    public static void closeResources(Connection connection, PreparedStatement preparedStatement,
                               ResultSet resultSet) throws SQLException {
        if (resultSet != null) resultSet.close();

        if (preparedStatement != null) preparedStatement.close();

        if (connection != null) connection.close();

    }

    public static void closeResources(Connection connection, PreparedStatement preparedStatement) throws SQLException {
        if (connection != null) connection.close();

        if (preparedStatement != null) preparedStatement.close();
    }
}
