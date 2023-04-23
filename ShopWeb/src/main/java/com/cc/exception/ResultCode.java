package com.cc.exception;

/**
 * 描述：     异常枚举
 */
public enum ResultCode {
    /**
     * 客户端请求成功，即处理成功
     */
    SUCCESS(200, "操作成功"),
    NEED_USER_NAME(1, "用户名不能为空"),
    NEED_PASSWORD(2, "密码不能为空"),
    PASSWORD_TOO_SHORT(3, "密码长度不能小于8位"),
    NAME_EXISTED(4, "不允许重名"),
    INSERT_FAILED(5, "插入失败，请重试"),
    WRONG_PASSWORD(6, "密码错误"),
    NEED_LOGIN(7, "用户未登录"),
    UPDATE_FAILED(8, "更新失败"),
    NEED_ADMIN(9, "无管理员权限"),
    PARA_NOT_NULL(10, "参数不能为空"),
    CREATE_FAILED(11, "新增失败"),
    REQUEST_PARAM_ERROR(12, "参数错误"),
    DELETE_FAILED(13, "删除失败"),
    MKDIR_FAILED(14, "文件夹创建失败"),
    UPLOAD_FAILED(15, "图片上传失败"),
    NOT_SALE(16, "商品状态不可售"),
    NOT_ENOUGH(17, "商品库存不足"),
    CART_EMPTY(18, "购物车已勾选的商品为空"),
    NO_ENUM(19, "未找到对应的枚举"),
    NO_ORDER(20, "订单不存在"),
    NOT_YOUR_ORDER(21, "订单不属于你"),
    WRONG_ORDER_STATUS(22, "订单状态不符"),
    SYSTEM_ERROR(30, "系统异常，请从控制台或日志中查看具体错误信息");

    /**
     * 异常码
     */
    Integer code;
    /**
     * 异常信息
     */
    String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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
}
