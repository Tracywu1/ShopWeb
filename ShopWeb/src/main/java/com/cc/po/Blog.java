package com.cc.po;

import java.sql.Timestamp;
import java.util.List;

public class Blog {
    /**
     * 动态ID
     */
    private Integer id;
    /**
     * 所属店铺ID
     */
    private Integer storeId;
    /**
     * 动态内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Timestamp  createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", storeId=" + storeId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
