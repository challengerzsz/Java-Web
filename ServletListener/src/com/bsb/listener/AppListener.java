package com.bsb.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class AppListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        Map<String,String> countries = new HashMap<>();
        countries.put("China", "BeiJing");
        countries.put("Tha", "BKK");
        servletContext.setAttribute("countries", countries);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
