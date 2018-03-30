package DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    public static final String URL =
            "jdbc:mysql://127.0.0.1:3306/ttms?useSSL=false&useUnicode=true&characterEncoding=UTF-8";

    public static final String USERNAME = "root";

    public static final String PASSWORD = "agytorudhcv11";
    public static Connection connection = null;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
