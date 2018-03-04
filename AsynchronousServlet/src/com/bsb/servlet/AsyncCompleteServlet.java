package com.bsb.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AsyncCompleteServlet extends HttpServlet {
    private static final long serialVersionUID = 78234L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        final PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html><head><title>" + "Async Servlet</title></head>");
        printWriter.println("<body><div id='progress'></div>");
        final AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(60000);
        asyncContext.start(() -> {
            System.out.println("new Thread:" + Thread.currentThread());
            for (int i = 0; i < 10; i++) {
                printWriter.println("<script>");
                printWriter.println("document.getElementById(" + "'progress').innerHTML = '" + (i * 10) + "% complete'");
                printWriter.println("</script>");
                printWriter.flush();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            printWriter.println("<script>");
            printWriter.println("document.getElementById(" + "'progress').innerHTML = 'DONE'");
            printWriter.println("</script>");
            printWriter.println("</body></html>");
            asyncContext.complete();
        });
    }
}
