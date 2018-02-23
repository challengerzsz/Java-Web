package com.bsb.learning.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PreferenceServlet", urlPatterns = {"/preference"})
public class PreferenceServlet extends HttpServlet{
    private static final long serialVersionUID = 888L;
    public static final String MENU = "<div class=\"\" style=\"background:#e8e8e8;\" padding:15px>\n" +
            "      <a href=\"cookieClass\">Cookies Class</a>&nbsp;&nbsp;\n" +
            "      <a href=\"cookieInfo\">Cookies Info</a>&nbsp;&nbsp;\n" +
            "      <a href=\"preference\">Preference</a>\n" +
            "    </div>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>Preference</title>\n" +
                "    <style media=\"screen\">\n" +
                "      table {font-size: small; background: NavajoWhite}\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body>" + MENU + "Please select the values below:\n" +
                "      <form method=\"post\">\n" +
                "        <table>\n" +
                "          <tr>\n" +
                "            <td>Title Font Size</td>\n" +
                "            <td>\n" +
                "              <select  name=\"titleFontSize\">\n" +
                "                <option>large</option>\n" +
                "                <option>x-large</option>\n" +
                "                <option>xx-large</option>\n" +
                "              </select>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "\n" +
                "          <tr>\n" +
                "            <td>Title Style & Weight</td>\n" +
                "            <td>\n" +
                "              <select name=\"titleStyleAndWeight\" multiple>\n" +
                "                <option>italic</option>\n" +
                "                <option>bold</option>\n" +
                "              </select>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "\n" +
                "          <tr>\n" +
                "              <td>Max. Records in Table</td>\n" +
                "              <td>\n" +
                "                <select name=\"maxRecords\">\n" +
                "                  <option>5</option>\n" +
                "                  <option>10</option>\n" +
                "                </select>\n" +
                "              </td>\n" +
                "          </tr>\n" +
                "\n" +
                "          <tr>\n" +
                "            <td rowspan=\"2\">\n" +
                "              <input type=\"submit\" name=\"\" value=\"Set\">\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </form>\n" +
                "  </body>\n" +
                "</html>\n");
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String maxRecords = req.getParameter("maxRecords");
        String[] titleStyleAndWeight = req.getParameterValues("titleStyleAndWeight");
        String titleFontSize = req.getParameter("titleFontSize");

        resp.addCookie(new Cookie("maxRecords", maxRecords));
        System.out.println("add maxRecordsCookie successfully");
        resp.addCookie(new Cookie("titleFontSize", titleFontSize));

        //delete titleFontWeight and titleFontStyle cookies first
        //Delete cookies by adding a cookies with the maxAge = 0;
        Cookie cookie = new Cookie("titleFontWeight", "");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);

        cookie = new Cookie("titleFontStyle", "");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);

        if (titleStyleAndWeight != null) {
            for (String style : titleStyleAndWeight) {
                if (style.equals("bold")) {
                    resp.addCookie(new Cookie("titleFontWeight", "bold"));
                } else if (style.equals("italic")) {
                    resp.addCookie(new Cookie("titleFontWeight", "italic"));
                }
            }
        }

        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>Preference</title>\n" +
                "  </head>\n" +
                "  <body>" + MENU + "Your Preference has been set.\n" +
                "    <br><br>Max. Records in Table : " + maxRecords +
                "    <br>Title Font Size :" + titleFontSize +
                "    <br>Title Font Style & Weight:");
        //titleStyleAndWeight will be null if none of the options was selected
        if (titleStyleAndWeight != null) {
            printWriter.println("<ul>");
            for (String style : titleStyleAndWeight) {
                printWriter.print("<li>" + style + "</li>" );
            }
            printWriter.println("</ul>");
        }
        printWriter.println("  </body>\n" +
                "</html>");
        printWriter.close();
    }
}
