package com.cc.po;

import java.sql.Timestamp;

public class Message {
    /**
     * 发送者id
     */
    private Integer fromId;
    /**
     * 发送者名字
     */
    private String fromName;
    /**
     * 接收者id
     */
    private Integer toId;
    /**
     * 接收者名字
     */
    private String toName;
    /**
     * 发送的内容
     */
    private String message;
    /**
     * 发送时间
     */
    private Timestamp createTime;

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "fromId='" + fromId + '\'' +
                ", fromName='" + fromName + '\'' +
                ", toId='" + toId + '\'' +
                ", toName='" + toName + '\'' +
                ", message='" + message + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
