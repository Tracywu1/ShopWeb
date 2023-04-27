package com.cc.po;

import com.cc.common.Constants;

import java.sql.Timestamp;

public class Message {
    /**
     * 消息ID
     */
    private Integer id;
    /**
     * 发送者
     */
    private User sender;
    /**
     * 接收者
     */
    private User receiver;
    /**
     * 消息类型
     */
    private Integer type;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 接收时间
     */
    private Timestamp receiveTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Timestamp getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Timestamp receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", receiveTime=" + receiveTime +
                '}';
    }
}
