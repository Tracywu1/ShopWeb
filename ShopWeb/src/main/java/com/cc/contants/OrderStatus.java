package com.cc.contants;

public enum OrderStatus {
    /**
     * 未发货
     */
    NOT_SHIPPED(1,"未发货"),
    /**
     * 已发货
     */
    DELIVERED(2,"已发货"),
    /**
     * 已收货
     */
    RECEIVED(3,"已收货"),
    /**
     * 售后
     */
    AFTER_SALES_SERVICE(4,"售后");

    private int num;

    private String status;

    OrderStatus(int num, String status) {
        this.num = num;
        this.status = status;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
