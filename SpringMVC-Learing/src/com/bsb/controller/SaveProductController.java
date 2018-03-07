package com.bsb.controller;

import com.bsb.domain.Product;
import com.bsb.form.ProductForm;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveProductController implements Controller {

    private static final Log logger = LogFactory.getLog(SaveProductController.class);

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        logger.info("SaveProductController called");
        ProductForm productForm = new ProductForm();

        //populate action properties
        productForm.setName(request.getParameter("name"));
        productForm.setDesc(request.getParameter("desc"));
        productForm.setPrice(request.getParameter("price"));

        //create model
        Product product = new Product();
        product.setName(productForm.getName());
        product.setDesc(productForm.getDesc());
        try {
            product.setPrice(Float.parseFloat(productForm.getPrice()));
        } catch (NumberFormatException ex) {}

        //insert code to save product


//        request.setAttribute("product", product);
//        System.out.println("errors isEmpty added");
//        //store errors and form in a scope variable for the view
//        request.setAttribute("form", productForm);
//        System.out.println("had some errors added");
//        return null;
        return new ModelAndView("WEB-INF/jsp/ProductDetails.jsp", "product", product);
    }

}
