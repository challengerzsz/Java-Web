package com.bsb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Controller {
    //处理请求和响应
    String handleRequest(HttpServletRequest request, HttpServletResponse response);

}
