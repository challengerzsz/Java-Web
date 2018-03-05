package com.bsb.listener;

import com.bsb.servlet.FirstServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

@WebListener
public class DynRegListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();

        Servlet firstServlet = null;
        try {
            firstServlet = servletContext.createServlet(FirstServlet.class);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        if (firstServlet != null && firstServlet instanceof FirstServlet) {
            ((FirstServlet) firstServlet).setName("Dynamically registered servlet");
        }

        ServletRegistration.Dynamic dynamic = servletContext.addServlet("firstServlet", firstServlet);
        dynamic.addMapping("/dynamic");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
