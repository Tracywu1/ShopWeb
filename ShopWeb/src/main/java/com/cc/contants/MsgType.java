package com.cc.contants;

public enum MsgType {
    /**
     * 系统消息
     */
    SYSTEM_MSG(1,"系统消息"),

    /**
     * 店铺动态消息
     */
    STORE_BLOG_MSG(2,"店铺动态提醒");

    private int num;

    private String type;

    MsgType(int num, String type) {
        this.num = num;
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
