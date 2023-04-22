package com.cc.po;

import java.sql.Timestamp;

/**
 * 记录评论更新的日志
 */
public class CommentLog {

    private Integer id;
    private Timestamp createTime;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CommentLog{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", description='" + description + '\'' +
                '}';
    }
}
