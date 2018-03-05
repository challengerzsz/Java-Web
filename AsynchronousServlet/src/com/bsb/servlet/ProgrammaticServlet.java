package com.bsb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ProgrammaticServlet", urlPatterns = "/prog")
public class ProgrammaticServlet extends HttpServlet{
    private static final long serialVersionUID = 87620L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.authenticate(resp)) {
            resp.setContentType("text/html");
            PrintWriter printWriter = resp.getWriter();
            printWriter.println("验证成功");
            System.out.println("验证成功");
        } else {
            //user is not authenticate
            System.out.println("验证失败");
        }
    }
}
