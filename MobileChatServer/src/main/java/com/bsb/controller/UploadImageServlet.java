package com.bsb.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@WebServlet(name = "UploadImage", urlPatterns = "/uploadImage")
public class UploadImageServlet extends HttpServlet{
    private static final String UPLOAD_DIR = "/image";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println(this.getServletContext().getContextPath() + UPLOAD_DIR);
        doPost(req, resp);
//        req.getRequestDispatcher("/WEB-INF/jsp/uploadImage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=utf-8");

        ServletContext servletContext = this.getServletConfig().getServletContext();

        String fileName = "zsz.jpg";
        String realPath = servletContext.getRealPath(UPLOAD_DIR) + "/";
        String filePath = realPath + fileName;

        System.out.println("完整路径" + filePath);


//        FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));

    }
}
