package com.bsb.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebFilter(filterName = "DownloadCounterFilter", urlPatterns = "/*")
public class DownloadCounterFilter implements Filter {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    //Property对象的store和load方法用来存储和检索列表
    private Properties downloadLog;
    private File logFile;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("DownloadCounterFilter");
        String appPath = filterConfig.getServletContext().getRealPath("/");
        logFile = new File(appPath, "downloadLog.txt");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        downloadLog = new Properties();
        try {
            downloadLog.load(new FileReader(logFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        final String uri = httpServletRequest.getRequestURI();
        executorService.execute(() -> {
            String property = downloadLog.getProperty(uri);
            if (property == null) {
                downloadLog.setProperty(uri, "1");
            } else {
                int count = 0;
                try {
                    count = Integer.parseInt(property);
                } catch (NumberFormatException nfe) {
                }
                count++;
                downloadLog.setProperty(uri, Integer.toString(count));
            }
            try {
                downloadLog.store(new FileWriter(logFile), "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        executorService.shutdown();
    }
}
