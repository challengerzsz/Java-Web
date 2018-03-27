package com.bsb.controller;

import com.bsb.model.UserDynamicModel;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name =  "UpdateDynamicController", urlPatterns = "/updateDynamic")
public class UpdateDynamicController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/updateDynamic.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String serverIp = "http://" + req.getServerName() + ":" + req.getServerPort();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        String realPath = servletContext.getContextPath();
        String xdPath = "/image/";
        String newFilePath = serverIp + realPath + xdPath;

        UserDynamicModel userDynamicModel = new UserDynamicModel();
        try {
            userDynamicModel.updateDynamic(req, resp, newFilePath);
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
