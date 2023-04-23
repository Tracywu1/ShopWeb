package com.cc.exception;

public class MyRunTimeException extends RuntimeException {
    private final Integer code;
    private final String msg;

    public MyRunTimeException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public MyRunTimeException(ResultCode resultCode) {
         this(resultCode.getCode(),resultCode.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
