package com.cc.po;

import com.cc.contants.IsDefault;

import java.sql.Timestamp;

import static com.cc.contants.IsDefault.NO;

public class Address {
    /**
     * 地址ID
     */
    private Integer id;
    /**
     * 所属用户
     */
    private User user;
    /**
     * 收货人
     */
    private String consignee;
    /**
     * 电话号码
     */
    private String phoneNum;
    /**
     * 详细地址
     */
    private String detail;
    /**
     * 是否设为默认地址
     */
    private IsDefault isDefault = NO;
    /**
     * 创建时间
     */
    private Timestamp  createTime;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    public IsDefault getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(IsDefault isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", user=" + user +
                ", consignee='" + consignee + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", detail='" + detail + '\'' +
                ", isDefault=" + isDefault +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
