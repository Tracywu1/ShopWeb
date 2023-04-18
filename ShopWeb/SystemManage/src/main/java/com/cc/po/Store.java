package com.cc.po;

public class Store {
    /**
     * 店铺ID
     */
    private Integer id;
    /**
     * 店铺管理员ID
     */
    private Integer userId;
    /**
     * 店铺名称
     */
    private String name;
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
     * 月均销量
     */
    private Integer aveMonthSales;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getAveMonthSales() {
        return aveMonthSales;
    }

    public void setAveMonthSales(Integer aveMonthSales) {
        this.aveMonthSales = aveMonthSales;
    }
}
