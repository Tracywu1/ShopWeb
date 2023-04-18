package com.cc.po;

public class Message {
    /**
     * 消息ID
     */
    private Integer id;
    /**
     * 用户ID
     */
    private User user;
    /**
     * 消息类型
     */
    private String type;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 接收时间
     */
    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
