package com.cc.po;

import java.util.Date;
import java.util.List;

public class Chat {
    /**
     * 聊天ID
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
     * 聊天内容
     */
    private String content;
    /**
     * 发送时间
     */
    private Date sendTime;

    private List<User> userList;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
