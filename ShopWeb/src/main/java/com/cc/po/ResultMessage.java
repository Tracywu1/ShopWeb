package com.cc.po;

public class ResultMessage {
    private boolean isSystem;//判断是不是系统消息

    private String fromName;//若为系统消息则为null,不然则为发送给的用户的用户名

    private Object message;//如果使系统消息就是数组

    public boolean isSystem() {
        return isSystem;
    }

    public void setSystem(boolean system) {
        isSystem = system;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultMessage{" +
                "isSystem=" + isSystem +
                ", fromName='" + fromName + '\'' +
                ", message=" + message +
                '}';
    }
}
