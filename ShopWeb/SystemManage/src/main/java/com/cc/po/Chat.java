package com.cc.po;

public class Chat {
    /**
     * 聊天ID
     */
    private Integer id;
    /**
     * 发生者ID
     */
    private Integer senderId;
    /**
     * 接收者ID
     */
    private Integer receiverId;
    /**
     * 聊天内容
     */
    private String content;
    /**
     * 发送时间
     */
    private String sendTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
