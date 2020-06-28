package com.study.cloud.common.eume;

public enum ErrorCode {

    ERROR_SYSTEM(-1,"系统服务异常"),
    ERROR_UNKNOWN(99999,"发生了什么?");

    private Integer code;
    private String message;
    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
