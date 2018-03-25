package com.bsb.service.impls;

import com.alibaba.fastjson.JSON;
import com.bsb.dao.impls.UserDao;
import com.bsb.pojo.User;
import com.bsb.service.IUserService;
import com.bsb.utils.MatchUtil.MatchRegexUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class UserService implements IUserService{
    private UserDao userDao = new UserDao();

    public UserService() throws IOException {
    }

    @Override
    public void login(String username, String password, HttpServletRequest request,
                         HttpServletResponse response) throws SQLException, IOException, ServletException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        HashMap<String,Object> responseMap = new HashMap<>();
        List<User> friends = userDao.login(username, password);
        PrintWriter printWriter = response.getWriter();

        if (friends != null) {
//            System.out.println(JSON.toJSONString(friends));
//            request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
            responseMap.put("status", "1");
            responseMap.put("massage", "登陆成功!");
            responseMap.put("friends", friends);
            printWriter.write(JSON.toJSONString(responseMap));
            printWriter.close();
        }
//        request.getRequestDispatcher("/WEB-INF/jsp/failed.jsp").forward(request, response);

        responseMap.put("status", "0");
        responseMap.put("massage", "登陆失败!");
        printWriter.write(JSON.toJSONString(responseMap));
        responseMap.clear();

        printWriter.close();
//        return false;
    }

    @Override
    public void register(String username, String password, String confirmedPassword, String email,
                            HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");


        HashMap<String,String> responseMap = new HashMap<>();
        PrintWriter printWriter = response.getWriter();

        if (password.equals(confirmedPassword) && MatchRegexUtil.checkEmail(email)) {
            if (userDao.register(username, password, email)) {
                responseMap.put("status", "1");
                responseMap.put("type", "用户注册成功!");
                printWriter.write(JSON.toJSONString(responseMap));
                responseMap.clear();
            }
        }

        responseMap.put("status", "0");
        responseMap.put("massage", "用户注册失败或用户名已存在!");
        printWriter.write(JSON.toJSONString(responseMap));
        responseMap.clear();
        printWriter.close();
    }

    @Override
    public boolean updateUser(int id) {
        return false;
    }

    @Override
    public void queryUser(String username, HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        HashMap<String,Object> responseMap = new HashMap<>();
        PrintWriter printWriter = response.getWriter();

        User user = userDao.queryUser(username);

        if (user != null) {
            responseMap.put("status", "1");
            responseMap.put("massage", "用户查询成功!");
            responseMap.put("user", user);
            printWriter.write(JSON.toJSONString(responseMap));
            responseMap.clear();
            printWriter.close();
        }

        responseMap.put("status", "0");
        responseMap.put("massage", "查询用户失败");
        printWriter.write(JSON.toJSONString(responseMap));
        responseMap.clear();
        printWriter.close();
    }

    @Override
    public List<User> queryAllUsers() {
        return null;
    }
}
