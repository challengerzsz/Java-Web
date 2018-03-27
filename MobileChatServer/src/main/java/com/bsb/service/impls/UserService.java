package com.bsb.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bsb.dao.impls.UserDao;
import com.bsb.pojo.User;
import com.bsb.service.IUserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService implements IUserService {

    private UserDao userDao = new UserDao();
    private JSONObject jsonObject = new JSONObject();
    private JSONArray jsonArray = new JSONArray();


//    // 上传文件存储目录
//    private static final String UPLOAD_DIRECTORY = "upload";
//
//    // 上传配置
//    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
//    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
//    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    public UserService() throws IOException {
    }

    @Override
    public void login(String username, String password, HttpServletRequest request,
                      HttpServletResponse response) throws SQLException, IOException, ServletException {

        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");

        boolean logResult = userDao.login(username, password);
        String userImageUrl = userDao.queryUserImage(username);

        PrintWriter printWriter = response.getWriter();

        if (logResult) {

            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);

            jsonObject.put("status", "1");
            jsonObject.put("message", "登陆成功!");
            if (userImageUrl != null) {
                jsonObject.put("image_url", userImageUrl);
            } else {
                jsonObject.put("image_url", null);
            }
            printWriter.println(jsonObject);
            printWriter.close();
        }
//        request.getRequestDispatcher("/WEB-INF/jsp/failed.jsp").forward(request, response);

        jsonObject.put("status", "0");
        jsonObject.put("message", "登陆失败!");
        printWriter.println(jsonObject);
        printWriter.close();
//        return false;
    }

    @Override
    public void register(String username, String password, String confirmedPassword, String email,
                         HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");

        boolean result = userDao.register(username, password);

        PrintWriter printWriter = response.getWriter();

//        if (password.equals(confirmedPassword) && MatchRegexUtil.checkEmail(email)) {
        if (result) {
            jsonObject.put("status", "1");
            jsonObject.put("message", "用户注册成功!");
            printWriter.println(jsonObject);
            printWriter.close();
        }
//        }

        jsonObject.put("status", "0");
        jsonObject.put("message", "用户注册失败或用户名已存在!");
        printWriter.write(JSON.toJSONString(jsonObject));
        printWriter.close();
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");

        PrintWriter printWriter = response.getWriter();
        //创建会话
        HttpSession session = request.getSession();
        //注销session
        session.invalidate();
        jsonObject.put("status", "1");
        jsonObject.put("message", "用户成功注销!");
        printWriter.println(jsonObject);
        printWriter.close();
    }

    @Override
    public boolean updateUser(String username) {
        return false;
    }

    @Override
    public void queryUser(String username, HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        HttpSession session = request.getSession(true);
        System.out.println("正在操作的是" + session.getAttribute("username"));

        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");

        PrintWriter printWriter = response.getWriter();

        User user = userDao.queryUser(username);

        if (user != null) {
            jsonObject.put("status", "1");
            jsonObject.put("message", "用户查询成功!");
            jsonObject.put("user", user);
            printWriter.println(jsonObject);
            printWriter.close();
        }

        jsonObject.put("status", "0");
        jsonObject.put("message", "查询用户失败");
        printWriter.println(jsonObject);
        printWriter.close();
    }

    @Override
    public List<User> queryAllUsers() {
        return null;
    }

    @Override
    public void uploadImage(HttpServletRequest request, HttpServletResponse response, String newFilePath) throws IOException, SQLException, FileUploadException {

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("utf-8");
        List item = upload.parseRequest(request);
        Map param = new HashMap();
        for (Object object : item) {
            FileItem fileItem = (FileItem) object;
            if (!fileItem.isFormField()) {
                param.put(fileItem.getFieldName(), fileItem.getInputStream());
            }
        }


        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter printWriter = response.getWriter();

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");


        //返回给用户的url
        String finalNewFilePath = newFilePath + username + ".jpg";
        System.out.println("返回给用户的url" + finalNewFilePath);

        //真正保存上传图片的路径
        ServletContext context = request.getServletContext();
        String saveFilePath = context.getRealPath("/");
        String savePath = saveFilePath + "/image/" + username + ".jpg";
        File finalFile = new File(savePath);


        byte[] buffer = new byte[1024];

        //输入输出流处理请求更新的图片
        try (

                InputStream inputStream = (InputStream) param.get("userImage");

                FileOutputStream fileOutputStream = new FileOutputStream(finalFile)
        ) {

            while (inputStream.read(buffer) != -1) {
                fileOutputStream.write(buffer);
            }
        } catch (IOException e) {

        }

        boolean result = userDao.uploadImage(username, finalNewFilePath);

        if (result) {
            jsonObject.put("status", "1");
            jsonObject.put("message", "上传图片成功");
            printWriter.println(jsonObject);
            printWriter.close();
        } else {
            jsonObject.put("status", "0");
            jsonObject.put("message", "上传图片失败!");
            printWriter.println(jsonObject);
            printWriter.close();
        }
    }
}
