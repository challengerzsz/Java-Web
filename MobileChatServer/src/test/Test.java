import com.bsb.utils.DBUtil.DBCPUtil;
import com.bsb.utils.MatchUtil.MatchRegexUtil;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws IOException, SQLException {
//        String SQL = "select * from user_table";
//        BasicDataSource basicDataSource = DBCPUtil.getBasicDataSource();
//        Connection connection = basicDataSource.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        while (resultSet.next()) {
//            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) +
//                    " " + resultSet.getString(3));
//        }
//
//        DBCPUtil.closeResources(connection, preparedStatement, resultSet);
//        File file = new File("src/resources/db.properties");
//        System.out.println(file.getAbsolutePath());

//        String email = "abc123@qq.com";
//        String email1 = "111";
//        System.out.println(MatchRegexUtil.checkEmail(email1));
        File imageFile = new File("C:\\Users\\66490\\Desktop\\user_image\\zsz.jpg");
        File newFile = new File("C:\\Users\\66490\\Desktop\\newUser_image.jpg");

        System.out.println("abspath" + imageFile.getAbsolutePath());
        System.out.println("path" + imageFile.getPath());
        byte[] buffer = new byte[1024];

        FileInputStream fileInputStream = new FileInputStream(imageFile);
        FileOutputStream fileOutputStream = new FileOutputStream(newFile);

        while (fileInputStream.read(buffer) != -1) {
            fileOutputStream.write(buffer);
        }

        if (fileInputStream != null) {
            fileInputStream.close();
        }
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }

    }
}
