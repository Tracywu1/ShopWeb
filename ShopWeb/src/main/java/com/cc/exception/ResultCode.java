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
    WRONG_PASSWORD_EXCESS(7,"输入错误已超过三次，请在一分钟后重试"),
    WRONG_CHECK_CODE(8,"验证码错误"),
    UPDATE_FAILED(9, "更新失败"),
    NEED_ADMIN(10, "无管理员权限"),
    PARA_NOT_NULL(11, "参数不能为空"),
    CREATE_FAILED(12, "新增失败"),
    REQUEST_PARAM_ERROR(13, "参数错误"),
    DELETE_FAILED(14, "删除失败"),
    MKDIR_FAILED(15, "文件夹创建失败"),
    UPLOAD_FAILED(16, "图片上传失败"),
    NOT_SALE(17, "商品状态不可售"),
    NOT_ENOUGH(18, "商品库存不足"),
    CART_EMPTY(19, "购物车已勾选的商品为空"),
    NO_ENUM(20, "未找到对应的枚举"),
    NO_ORDER(21, "订单不存在"),
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
