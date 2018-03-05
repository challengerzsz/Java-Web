package com.bsb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FirstServlet extends HttpServlet {
    private static final long serialVersionUID = -6045338L;

    private String name;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text.html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html lang=\"en\" dir=\"ltr\">\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>First servlet</title>\n" +
                "  </head>\n" +
                "  <body>" + name + "</body>\n" +
                "</html>");
    }

    public void setName(String name) {
        this.name = name;
    }
}
