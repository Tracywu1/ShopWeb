package com.cc.contants;

public enum ReturnStatus {
    /**
     * 待处理
     */
    TO_BE_PROCESSED(1,"待处理"),
    /**
     * 已处理
     */
    PROCESSED(2,"已处理"),
    /**
     * 已退款
     */
    REFUNDED(3,"已退款"),
    /**
     * 已取消
     */
    CANCELLED(4,"已取消");

    private int num;

    private String sstatus;

    ReturnStatus(int num, String sstatus) {
        this.num = num;
        this.sstatus = sstatus;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getSstatus() {
        return sstatus;
    }

    public void setSstatus(String sstatus) {
        this.sstatus = sstatus;
    }
}
