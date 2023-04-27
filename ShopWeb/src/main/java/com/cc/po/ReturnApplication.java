package com.cc.po;

import java.sql.Timestamp;

public class ReturnApplication {
    /**
     * 退货ID
     */
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 退货原因
     */
    private String reason;
    /**
     * 申请状态
     */
    private Integer status;
    /**
     * 退货时间
     */
    private Timestamp returnTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Timestamp returnTime) {
        this.returnTime = returnTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ReturnApplication{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderNo='" + orderNo + '\'' +
                ", reason='" + reason + '\'' +
                ", status=" + status +
                ", returnTime=" + returnTime +
                '}';
    }
}
