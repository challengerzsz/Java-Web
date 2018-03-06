package com.bsb.controller;

import com.bsb.domain.Product;
import com.bsb.form.ProductForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveProductController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        ProductForm productForm = new ProductForm();

        productForm.setName(request.getParameter("name"));
        productForm.setDesc(request.getParameter("desc"));
        productForm.setPrice(request.getParameter("price"));

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
        request.setAttribute("product", product);
        return "/WEB-INF/jsp/ProductDetails.jsp";
    }
}
