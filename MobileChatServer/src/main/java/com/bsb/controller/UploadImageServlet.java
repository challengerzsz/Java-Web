package com.bsb.controller;

import com.bsb.model.UserImageModel;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UploadImage", urlPatterns = "/uploadImage")
public class UploadImageServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println(this.getServletContext().getContextPath() + UPLOAD_DIR);
//        doPost(req, resp);
        req.getRequestDispatcher("/WEB-INF/jsp/uploadImage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String serverIp = req.getServerName() + ":" + req.getServerPort();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        String realPath = servletContext.getContextPath();
        String xdPath = "/image/";
        String newFilePath = serverIp + realPath + xdPath;

        UserImageModel userImageModel = new UserImageModel();
        try {
            userImageModel.uploadImage(req, resp, newFilePath);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

    }
}
