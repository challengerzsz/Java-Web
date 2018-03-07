package com.bsb.controller;

import com.bsb.domain.Product;
import com.bsb.form.ProductForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

    private static final Log logger = LogFactory.getLog(ProductController.class);

    @RequestMapping(value = "/product_input")
    public String inputProduct() {
        logger.info("inputProduct called");
        return "ProductForm";
    }

    @RequestMapping(value = "/product_save")
    public String saveProduct(ProductForm productForm, Model model) {
        logger.info("saveProduct called");

        Product product = new Product();

        product.setName(productForm.getName());
        product.setDesc(productForm.getDesc());
        try {
            product.setPrice(Float.parseFloat(productForm.getPrice()));
        } catch (NumberFormatException ex) {}

        model.addAttribute("product", product);
        return "ProductDetails";
    }
}
