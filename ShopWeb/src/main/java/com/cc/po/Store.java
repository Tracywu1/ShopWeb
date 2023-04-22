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
    private User manager;
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

    private List<Product> productList;

    private List<Blog> blogList;

    private List<Subscribe> subscribeList;

    private List<Cart> cartList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
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

    public List<Subscribe> getSubscribeList() {
        return subscribeList;
    }

    public void setSubscribeList(List<Subscribe> subscribeList) {
        this.subscribeList = subscribeList;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
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
        return "Store{" +
                "id=" + id +
                ", manager=" + manager +
                ", storeName='" + storeName + '\'' +
                ", description='" + description + '\'' +
                ", logo='" + logo + '\'' +
                ", fansNum=" + fansNum +
                ", aveMonthSales=" + aveMonthSales +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", productList=" + productList +
                ", blogList=" + blogList +
                ", subscribeList=" + subscribeList +
                ", cartList=" + cartList +
                '}';
    }
}
