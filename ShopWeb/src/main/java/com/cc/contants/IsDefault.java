package com.cc.contants;

public enum IsDefault {
    /**
     * 设置为默认地址
     */
    YES(1,"是"),

    /**
     * 不设置为默认地址
     */
    NO(2,"否");

    private int num;

    private String judge;

    IsDefault(int num, String judge) {
        this.num = num;
        this.judge = judge;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }
}
