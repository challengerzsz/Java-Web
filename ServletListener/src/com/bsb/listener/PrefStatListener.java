package com.bsb.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class PrefStatListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        long start = (long) servletRequest.getAttribute("start");
        long end = System.nanoTime();
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String uri = httpServletRequest.getRequestURI();
        System.out.println("time taken to execute " + uri + ":" + ((end - start) / 1000) + "microseconds");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        servletRequest.setAttribute("start", System.nanoTime());
    }
}
