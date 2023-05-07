package com.cc.po;

import java.sql.Timestamp;
import java.util.List;

public class Store {
    /**
     * 店铺ID
     */
    private Integer id;
    /**
     * 店铺管理员
     */
    private Integer managerId;
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 店铺介绍
     */
    private String description;
    /**
     * 店铺头像地址
     */
    private String logo;
    /**
     * 粉丝数量
     */
    private Integer fansNum;
    /**
     * 月均销量(不在数据库内)
     */
    private Integer monthlyAveSaleCount;
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

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
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

    public Integer getFansNum() {
        return fansNum;
    }

    public void setFansNum(Integer fansNum) {
        this.fansNum = fansNum;
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

    public Integer getMonthlyAveSaleCount() {
        return monthlyAveSaleCount;
    }

    public void setMonthlyAveSaleCount(Integer monthlyAveSaleCount) {
        this.monthlyAveSaleCount = monthlyAveSaleCount;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", managerId=" + managerId +
                ", storeName='" + storeName + '\'' +
                ", description='" + description + '\'' +
                ", logo='" + logo + '\'' +
                ", fansNum=" + fansNum +
                ", monthlyAveSaleCount=" + monthlyAveSaleCount +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
