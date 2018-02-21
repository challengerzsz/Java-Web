package com.bsb.learning.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CookieInfoServlet", urlPatterns = {"/cookieInfo"})
public class CookieInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 3829L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        StringBuilder styles = new StringBuilder();
        styles.append(".title {");

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                if (name.equals("titleFontSize")) {
                    styles.append("font-size:" + value + ";");
                } else if (name.equals("titleFontWeight")) {
                    styles.append("font-weight:" + value + ";");
                } else if (name.equals("titleFontStyle")) {
                    styles.append("font-style:" + value + ";");
                }
            }
        }
        styles.append("}");

        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>Cookie Info</title>\n" +
                "    <style media=\"screen\">" + styles.toString() + "</style>\n" +
                "  </head>\n" +
                "  <body>" + PreferenceServlet.MENU + "<div class=\"title\">\n" +
                "      Session Management with Cookies:\n" +
                "    </div>");
        printWriter.println("<div>");

        //cookies will be null if there's no cookie
        if (cookies == null) {
            printWriter.println("No cookies in this HTTP resp.");
        } else {
            printWriter.println("<br/>Cookies in the HTTP resp:");
            for (Cookie cookie : cookies) {
                printWriter.println("<br/>" + cookie.getName() + ":" + cookie.getValue());
            }
        }
        printWriter.println("</div>\n" +
                "  </body>\n" +
                "</html>");
        printWriter.close();
    }
}
