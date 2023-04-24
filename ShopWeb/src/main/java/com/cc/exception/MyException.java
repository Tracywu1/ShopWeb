package com.cc.exception;

/**
 * 统一异常描述类
 */
public class MyException extends RuntimeException {
    private final Integer code;
    private final String msg;

    public MyException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public MyException(ResultCode resultCode) {
         this(resultCode.getCode(),resultCode.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
