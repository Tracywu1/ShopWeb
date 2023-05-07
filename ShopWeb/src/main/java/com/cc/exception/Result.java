package com.cc.exception;


public class Result<T> {
    /**
     * 响应码    0:客户端请求有语法错误，不能被服务器所理解
     */
    private Integer code;
    /**
     * 响应信息 描述字符串
     */
    private String msg ;
    /**
     * 返回的数据
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 查询 成功响应
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }

    /**
     *  增删改 成功响应
     */
    public static <T> Result success() {
        return success(null);
    }

    public static Result error(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMsg(), null);
    }

    /**
     *  客户端请求有语法错误，不能被服务器所理解
     */
    public static <T> Result error(Integer code , String message) {
        return new Result<>(code, message, null);
    }

}