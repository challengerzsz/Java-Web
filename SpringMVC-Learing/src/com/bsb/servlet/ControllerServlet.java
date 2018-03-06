package com.bsb.servlet;

import com.bsb.domain.Product;
import com.bsb.form.ProductForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ControllerServlet", urlPatterns = {"/product_input", "/product_save",
        "/product_input.action", "/product_save.action"})
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1579L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }


    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        System.out.println(uri);
        int lastIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastIndex + 1);
        if (action.equals("product_input.action")) {
            //do nothing
        } else if (action.equals("product_save.action")) {
            //create form
            ProductForm productForm = new ProductForm();
            productForm.setName(req.getParameter("name"));
            productForm.setDesc(req.getParameter("desc"));
            productForm.setPrice(req.getParameter("price"));

            //create model
            Product product = new Product();
            product.setName(productForm.getName());
            product.setDesc(productForm.getDesc());
            try {
                System.out.println(productForm.getPrice());
                product.setPrice(Float.parseFloat(productForm.getPrice()));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }

            //save product
            req.setAttribute("product", product);
        }

        //forward to view
        String dispatchUrl = null;
        if (action.equals("product_input")) {
            dispatchUrl = "/WEB-INF/jsp/ProductForm.jsp";
        } else if (action.equals("product_save.action")) {
            dispatchUrl = "/WEB-INF/jsp/ProductDetails.jsp";
        }

        if (dispatchUrl != null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(dispatchUrl);
            dispatcher.forward(req,resp);
        }
    }
}
