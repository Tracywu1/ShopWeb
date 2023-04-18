package com.cc.po;

import java.util.Date;

public class Report {
    /**
     * 举报ID
     */
    private Integer id;
    /**
     * 用户ID
     */
    private User user;
    /**
     * 举报内容
     */
    private String content;
    /**
     * 举报时间
     */
    private Date time;
    /**
     * 处理状态
     */
    private String status;

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

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
