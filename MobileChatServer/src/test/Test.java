import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bsb.utils.DBUtil.DBCPUtil;
import com.bsb.utils.ImageUtil.ImagesUtil;
import com.bsb.utils.MatchUtil.MatchRegexUtil;
import data.Person;
import org.apache.commons.dbcp.BasicDataSource;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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


//        File imageFile = new File("C:\\Users\\66490\\Desktop\\user_image\\zsz.jpg");
//        File newFile = new File("C:\\Users\\66490\\Desktop\\newUser_image.jpg");
//
//        System.out.println("abspath" + imageFile.getAbsolutePath());
//        System.out.println("path" + imageFile.getPath());
//        byte[] buffer = new byte[1024];
//
//        FileInputStream fileInputStream = new FileInputStream(imageFile);
//        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
//
//        while (fileInputStream.read(buffer) != -1) {
//            fileOutputStream.write(buffer);
//        }
//
//        if (fileInputStream != null) {
//            fileInputStream.close();
//        }
//        if (fileOutputStream != null) {
//            fileOutputStream.close();
//        }


        // 1. 将集合或者对象转换成json字符串
//        Person p1 = new Person(1, "zhangsan");
//        Person p2 = new Person(2, "lisi");
//
//        System.out.println("对象转换成json字符串 ：" + JSON.toJSON(p1));
//
//        List<Person> list = new ArrayList<Person>();
//        list.add(p1);
//        list.add(p2);
//        System.out.println("集合转换成json字符串 ： " + JSON.toJSONString(list));
//
//        //2.  Json串反序列化成对象
//        Person p3 = JSON.parseObject("{\"id\":4,\"name\":\"liyang\"}", Person.class);
//        System.out.println("Json串反序列化成对象 : id: " + p3.getId() + " name :" + p3.getName());
//
//        // Json串反序列化成数组
//        List<Person> persons = JSON.parseArray("[{\"id\":5,\"name\":\"qiaodan\"},{\"id\":6,\"name\":\"kebi\"}]", Person.class);
//        for (Person person : persons) {
//            System.out.println("Json串反序列化成数组: " + person);
//
//        }
//        ImagesUtil.test();

//        while (true) {
//            int i = new Scanner(System.in).nextInt();
//            System.out.println((i / 10 + 1 ) * 10);
//        }

//        List list = new ArrayList();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        JSONObject jsonObjec = new JSONObject();
//        jsonObjec.put("list",list);
//        System.out.println(jsonObjec);
    }

}
