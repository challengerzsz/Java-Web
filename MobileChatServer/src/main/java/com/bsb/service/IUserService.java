package com.bsb.service;

import com.bsb.pojo.User;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IUserService {

    void login(String username, String password, HttpServletRequest request,
              HttpServletResponse response) throws IOException, SQLException, ServletException;

    void register(String username, String password, String confirmPassword, String email,
                     HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException;

    void logout(HttpServletRequest request, HttpServletResponse response) throws IOException;

    boolean updateUser(String username);

    void queryUser(String username, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException;

    List<User> queryAllUsers();

    void uploadImage(HttpServletRequest request, HttpServletResponse response, String newFilePath) throws IOException, SQLException, FileUploadException;
}
