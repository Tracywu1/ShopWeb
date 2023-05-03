package com.cc.po;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Product {
    /**
     * 商品ID
     */
    private Integer id;
    /**
     * 所属店铺
     */
    private Integer storeId;
    /**
     * 所属店铺名称
     */
    private String storeName;
    /**
     * 商品名称
     */
    private String productName;
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
    private BigDecimal price ;
    /**
     * 月销量（不在数据库内）
     */
    private Integer monthlySalesCount;
    /**
     * 库存
     */
    private Integer productCount;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
    /**
     * 评论列表
     */
    private List<Comment> commentList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
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

    public Integer getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public Integer getMonthlySalesCount() {
        return monthlySalesCount;
    }

    public void setMonthlySalesCount(Integer monthlySalesCount) {
        this.monthlySalesCount = monthlySalesCount;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", monthlySalesCount=" + monthlySalesCount +
                ", productCount=" + productCount +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", commentList=" + commentList +
                '}';
    }
}
