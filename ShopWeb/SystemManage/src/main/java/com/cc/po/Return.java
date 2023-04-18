package com.cc.po;

public class Return {
    /**
     * 退货ID
     */
    private Integer id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 订单ID
     */
    private Integer orderId;
    /**
     * 退货原因
     */
    private String reason;
    /**
     * 退货状态
     */
    private String status;
    /**
     * 退货时间
     */
    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
