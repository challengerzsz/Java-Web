package com.bsb.learning.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CookieClassServlet", urlPatterns = {"/cookieClass"})
public class CookieClassServlet extends HttpServlet {
    private static final long serialVersionUID = 837369L;

    private String[] methods = {"clone", "getComment", "getDomain", "getMaxAge",
            "getName", "getPath", "getSecure", "getValue", "getVersion", "isHttpOnly",
            "setComment", "setDomain", "setHttpOnly", "setMaxAge", "setPath", "setSecure",
            "setValue", "setVersion"};

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("cookieClass page");
        Cookie[] cookies = req.getCookies();
        Cookie maxRecordsCookies = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("maxRecords")) {
                    System.out.println("found maxRecordsCookies");
                    maxRecordsCookies = cookie;
                    break;
                }
            }
        }

        int maxRecords = 5; //default
        if (maxRecordsCookies != null) {
            try {
                System.out.println(maxRecordsCookies.getValue());
                maxRecords = Integer.parseInt(maxRecordsCookies.getValue());
            } catch (NumberFormatException ex) {
                //do nothing, use maxRecords default value
            }
        }

        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>Cookies Class</title>\n" +
                "  </head>\n" +
                "  <body>" + PreferenceServlet.MENU + "<div>\n" +
                "      Here are some of the methods in javax.servlet.http.Cookie\n" +
                "      <ul>");

        for (int i = 0; i < maxRecords; i++) {
            printWriter.print("<li>" + methods[i] + "</li>");
        }

        printWriter.println("</ul>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>");
    }
}
