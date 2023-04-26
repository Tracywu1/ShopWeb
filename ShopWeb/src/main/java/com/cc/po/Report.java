package com.cc.po;

import com.cc.common.Constants;

import java.sql.Timestamp;

public class Report {
    /**
     * 举报ID
     */
    private Integer id;
    /**
     * 举报用户
     */
    private User user;
    /**
     * 举报内容
     */
    private String content;
    /**
     * 举报时间
     */
    private Timestamp time;
    /**
     * 处理状态
     */
    private Constants.ReportStatus status = Constants.ReportStatus.TO_BE_PROCESSED;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Constants.ReportStatus getStatus() {
        return status;
    }

    public void setStatus(Constants.ReportStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", status=" + status +
                '}';
    }
}
