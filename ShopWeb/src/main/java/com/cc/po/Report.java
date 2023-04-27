package com.cc.po;

import java.sql.Timestamp;

public class Report {
    /**
     * 举报ID
     */
    private Integer id;
    /**
     * 举报用户ID
     */
    private Integer userId;
    /**
     * 举报内容
     */
    private String content;
    /**
     * 举报时间
     */
    private Timestamp reportTime;
    /**
     * 处理状态
     */
    private Integer status = 1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getReportTime() {
        return reportTime;
    }

    public void setReportTime(Timestamp reportTime) {
        this.reportTime = reportTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", reportTime=" + reportTime +
                ", status=" + status +
                '}';
    }
}
