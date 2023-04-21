package com.cc.contants;

public enum ReportStatus {
    TO_BE_PROCESSED(1,"待处理"),
    PROCESSED(2,"已处理"),
    REJECTED(3,"已驳回");

    private int num;

    private String status;

    ReportStatus(int num, String status) {
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
