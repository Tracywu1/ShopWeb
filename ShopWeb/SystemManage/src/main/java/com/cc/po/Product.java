package com.cc.po;

import java.sql.Timestamp;
import java.util.List;

public class Product {
    /**
     * 商品ID
     */
    private Integer id;
    /**
     * 创建者
     */
    private User creator;
    /**
     * 更新者
     */
    private User updater;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品描述
     */
    private String description;
    /**
     * 商品图片地址
     */
    private String image;
    /**
     * 商品价格
     */
    private Double price;
    /**
     * 库存
     */
    private Integer productCount;
    /**
     * 月销售量
     */
    private Integer saleCount;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;

    private List<OrderDetail> orderDetailList;

    private List<Comment> commentList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getUpdater() {
        return updater;
    }

    public void setUpdater(User updater) {
        this.updater = updater;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Double getPrice() {
        return price;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
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
        return "Product{" +
                "id=" + id +
                ", creator=" + creator +
                ", updater=" + updater +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", productCount=" + productCount +
                ", saleCount=" + saleCount +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", orderDetailList=" + orderDetailList +
                ", commentList=" + commentList +
                '}';
    }
}
