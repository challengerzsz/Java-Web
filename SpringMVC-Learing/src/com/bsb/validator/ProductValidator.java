package com.bsb.validator;

import com.bsb.form.ProductForm;

import java.util.ArrayList;
import java.util.List;

public class ProductValidator {
    public List<String> validate(ProductForm productForm) {
        List<String> errors = new ArrayList<>();
        String name = productForm.getName();
        //trim()方法去掉字符串前后的空格字符
        if ((name == null || name.trim().isEmpty())) {
            errors.add("Product must have a name!");
        }
        String price = productForm.getPrice();
        if ((price == null || price.trim().isEmpty())) {
            errors.add("Product must have price!");
            System.out.println("price is null");
        } else {
            try {
                System.out.println("errors");
                Float.parseFloat(price);
            } catch (NumberFormatException ex) {
                System.out.println("null price");
                errors.add("Invalid price value!");
            }
        }
        for (String tmp : errors) {
            System.out.println(tmp);
        }
        return errors;
    }
}
