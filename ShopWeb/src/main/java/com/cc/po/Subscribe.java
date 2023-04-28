package com.cc.po;

import java.sql.Timestamp;
import java.util.Date;

public class Subscribe {
    /**
     * 关注ID
     */
    private Integer id;
    /**
     * 用户
     */
    private Integer userId;
    /**
     * 店铺
     */
    private Integer storeId;
    /**
     * 关注时间
     */
    private Timestamp createTime;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "Subscribe{" +
                "id=" + id +
                ", userId=" + userId +
                ", storeId=" + storeId +
                ", createTime=" + createTime +
                '}';
    }
}
