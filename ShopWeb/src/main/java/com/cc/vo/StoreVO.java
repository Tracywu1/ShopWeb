package com.cc.vo;

import com.cc.po.Blog;
import com.cc.po.Product;
import com.cc.po.Subscribe;

import java.sql.Timestamp;
import java.util.List;

public class StoreVO {
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
     * 月均销量
     */
    private Integer aveMonthSales;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
    /**
     * 店铺的商品列表
     */
    private List<Product> productList;
    /**
     * 店铺的动态列表
     */
    private List<Blog> blogList;

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

    public Integer getAveMonthSales() {
        return aveMonthSales;
    }

    public void setAveMonthSales(Integer aveMonthSales) {
        this.aveMonthSales = aveMonthSales;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Blog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
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

    @Override
    public String toString() {
        return "StoreVO{" +
                "id=" + id +
                ", managerId=" + managerId +
                ", storeName='" + storeName + '\'' +
                ", description='" + description + '\'' +
                ", logo='" + logo + '\'' +
                ", fansNum=" + fansNum +
                ", aveMonthSales=" + aveMonthSales +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", productList=" + productList +
                ", blogList=" + blogList +
                '}';
    }
}
