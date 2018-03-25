package com.bsb.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class User {
    @JSONField(ordinal = 1)
    private String username;
    @JSONField(ordinal = 2)
    private String email;
    @JSONField(ordinal = 3)
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
