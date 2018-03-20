import com.bsb.utils.DBUtil.DBCPUtil;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws IOException, SQLException {
        String SQL = "select * from user_table";
        BasicDataSource basicDataSource = DBCPUtil.getBasicDataSource();
        Connection connection = basicDataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) +
                    " " + resultSet.getString(3));
        }

        DBCPUtil.closeResources(connection, preparedStatement, resultSet);
//        File file = new File("src/resources/db.properties");
//        System.out.println(file.getAbsolutePath());
    }
}
