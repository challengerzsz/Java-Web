package com.bsb.model;

import com.bsb.service.impls.UserService;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UserDynamicModel {
    private UserService userService = new UserService();

    public UserDynamicModel() throws IOException {
    }

    public void updateDynamic(HttpServletRequest request, HttpServletResponse response, String newFilePath) throws IOException, FileUploadException, SQLException {
        userService.updateDynamic(request, response, newFilePath);
    }

    public void queryDynamic(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        userService.queryDynamic(request, response);
    }
}
