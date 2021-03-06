package com.bsb.common;

/**
 * @author zeng
 */

public enum ResponseCode {

    /**
     * 成功
     */
    SUCCESS(1, "SUCCESS"),
    /**
     * 失败
     */
    ERROR(0, "ERROR");


    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
