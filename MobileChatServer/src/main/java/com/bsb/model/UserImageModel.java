package com.bsb.model;

import com.bsb.service.impls.UserService;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UserImageModel {

    private UserService userService = new UserService();

    public UserImageModel() throws IOException {
    }

    public void uploadImage(HttpServletRequest request, HttpServletResponse response, String newFilePath) throws IOException, SQLException, FileUploadException {
        userService.uploadImage(request, response, newFilePath);
    }
}
