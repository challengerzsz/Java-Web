package com.bsb.learning.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UrlTestServlet")
public class UrlTestServlet extends HttpServlet {

    private static final long serialVersionUID = 987654321L;

    private List<String> before;
    private List<String> after;


    @Override
    public void init() throws ServletException {
        //测试数据1-20两个集合存储
        before = new ArrayList<>(10);
        after = new ArrayList<>(10);

        for (int i = 1; i <= 10; i++) {
            before.add(String.valueOf(i));
        }
        for (int i = 11; i <= 20; i++) {
            after.add(String.valueOf(i));
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter("key");

        if (key != null && (key.equals("before") || key.equals("after"))) {
            //展示集合内容
            showAttractions(req, resp, key);
        } else {
            //展示主页
            showMainPage(req, resp);
        }
    }

    private void showMainPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html>\n" +
                "  <head>\n" +
                "    <title>numbers</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "  选择一个选项\n" +
                "  <br/><a href=\"?key=before\">before</a>\n" +
                "  <br/><a href=\"?key=after\">after</a>\n" +
                "  </body>\n" +
                "</html>\n");
    }

    private void showAttractions(HttpServletRequest req, HttpServletResponse resp, String key) throws IOException {
        int page = 1;
        String pageParameter = req.getParameter("page");
        if (pageParameter != null) {
            try {
                page = Integer.parseInt(pageParameter);
            } catch (NumberFormatException ex) {

            }
            if (page > 2) {
                page = 1;
            }
        }
        List<String> attractions = null;
        if (key.equals("before")) {
            attractions = before;
        } else if (key.equals("after")) {
            attractions = after;
        }
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html>\n" +
                "  <head>\n" +
                "    <title>numbers</title>\n" +
                "  </head>\n" +
                "  <body>");
        printWriter.println("<hr>page" + page + "<hr/>");
        int start = page * 5 - 5;
        for (int i = start; i < start + 5; i++) {
            printWriter.println(attractions.get(i) + "<br/>");
        }
        printWriter.print("<hr style='color:blue'/>" + "<a href='?key=" + key + "&page=1'>Page 1</a>");
        printWriter.println("&nbsp; <a href='?key=" + key + "&page=2'>Page 2</a>");
        printWriter.println("</body></html>");
    }


}
