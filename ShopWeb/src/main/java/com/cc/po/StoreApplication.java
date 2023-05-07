package com.cc.po;

import java.sql.Timestamp;

public class StoreApplication {
    /**
     * 店铺ID
     */
    private Integer id;
    /**
     * 申请用户ID
     */
    private Integer userId;
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 店铺介绍
     */
    private String description;
    /**
     * 申请状态
     */
    private Integer status;
    /**
     * 店铺头像地址
     */
    private String logo;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
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
        return "StoreApplication{" +
                "id=" + id +
                ", userId=" + userId +
                ", storeName='" + storeName + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", logo='" + logo + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
