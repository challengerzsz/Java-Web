package com.bsb.controller;

import com.bsb.domain.Product;
import com.bsb.form.ProductForm;
import com.bsb.validator.ProductValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SaveProductController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        ProductForm productForm = new ProductForm();

        productForm.setName(request.getParameter("name"));
        productForm.setDesc(request.getParameter("desc"));
        productForm.setPrice(request.getParameter("price"));

        //validate ProductForm
        ProductValidator productValidator = new ProductValidator();
        List<String> errors = productValidator.validate(productForm);
        if (errors.isEmpty()) {
            //create model
            Product product = new Product();
            product.setName(productForm.getName());
            product.setDesc(productForm.getDesc());
            product.setPrice(Float.parseFloat(productForm.getPrice()));
            //save product
            request.setAttribute("product", product);
            System.out.println("errors isEmpty added");
            return "/WEB-INF/jsp/ProductDetails.jsp";
        } else {
            //store errors and form in a scope variable for the view
            request.setAttribute("errors", errors);
            request.setAttribute("form", productForm);
            System.out.println("had some errors added");
            return "/WEB-INF/jsp/ProductForm.jsp";
        }

    }
}
