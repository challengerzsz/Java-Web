package com.bsb.servlet;

import com.bsb.controller.InputProductController;
import com.bsb.controller.SaveProductController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DispatcherServlet", urlPatterns = {"/product_input", "/product_save",
        "/product_input.action", "/product_save.action"})
public class DispatcherServlet extends HttpServlet{
    private static final long serialVersionUID = 748495L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        int lastIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastIndex + 1);

        String dispatchUrl = null;
        if (action.equals("product_input")) {
            InputProductController controller = new InputProductController();
            dispatchUrl = controller.handleRequest(req, resp);
        } else if (action.equals("product_save.action")) {
            SaveProductController controller = new SaveProductController();
            dispatchUrl = controller.handleRequest(req, resp);
        }

        if (dispatchUrl != null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(dispatchUrl);
            dispatcher.forward(req,resp);
        }
    }

}
