package com.bsb.filter;

import javax.security.sasl.SaslException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "ImageProtectorFilter", urlPatterns = {"*.png", "*.jpg", "*.gif"})
public class ImageProtectorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("ImageProtectorFilter");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //如果get到的referer非空则说明图片链接为点击应用连接跳转至图片链接 则显示图片
        // 若get到为null则说明是直接访问图片 要阻止下载
        String referrer = httpServletRequest.getHeader("referer");

        System.out.println("referrer: " + referrer);
        if (referrer != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            throw new SaslException("Image not available!");
        }
    }

    @Override
    public void destroy() {

    }
}
