package com.bsb.service;

import com.bsb.domain.Product;

public interface ProductService {

    Product add(Product product);

    Product get(long id);
}
