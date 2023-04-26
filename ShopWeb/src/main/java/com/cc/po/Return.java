package com.cc.po;

import com.cc.common.Constants;

import java.sql.Timestamp;

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
    private Constants.ReturnStatus status = Constants.ReturnStatus.TO_BE_PROCESSED;
    /**
     * 退货时间
     */
    private Timestamp time;

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

    public Constants.ReturnStatus getStatus() {
        return status;
    }

    public void setStatus(Constants.ReturnStatus status) {
        this.status = status;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Return{" +
                "id=" + id +
                ", user=" + user +
                ", order=" + order +
                ", reason='" + reason + '\'' +
                ", status=" + status +
                ", time=" + time +
                '}';
    }
}
