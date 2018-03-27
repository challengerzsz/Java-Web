package com.bsb.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class Dynamic {
    @JSONField(ordinal = 1)
    private String username;
    @JSONField(ordinal = 2)
    private String userImageUrl;
    @JSONField(ordinal = 3)
    private String contentText;
    @JSONField(ordinal = 4)
    private String contentImageUrl;
    @JSONField(ordinal = 5)
    private String updateDate;

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getContentImageUrl() {
        return contentImageUrl;
    }

    public void setContentImageUrl(String contentImageUrl) {
        this.contentImageUrl = contentImageUrl;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
