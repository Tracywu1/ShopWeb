package com.cc.po;

import java.util.Date;

public class Return {
    /**
     * 退货ID
     */
    private Integer id;
    /**
     * 用户
     */
    private User user;
    /**
     * 订单
     */
    private Order order;
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
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
