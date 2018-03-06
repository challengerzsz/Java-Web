package com.bsb.domain;

import java.io.Serializable;

public class Product implements Serializable {

    private static final long serialVersionUID = 748392348L;

    private String name;
    private String desc;
    private float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
