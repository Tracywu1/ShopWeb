package com.cc.po;

import com.cc.contants.OrderStatus;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Order {
    /**
     * 订单ID
     */
    private Integer id;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 所属用户
     */
    private User user;
    /**
     * 订单数量
     */
    private Integer count;
    /**
     * 订单状态
     */
    private OrderStatus status = OrderStatus.NOT_SHIPPED;
    /**
     * 订单金额
     */
    private Double totalPrice;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;

    private List<OrderDetail> orderDetailList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", user=" + user +
                ", count=" + count +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", orderDetailList=" + orderDetailList +
                '}';
    }
}
