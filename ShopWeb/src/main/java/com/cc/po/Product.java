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
     * 创建者
     */
    private Integer createId;
    /**
     * 更新者
     */
    private Integer updateId;
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

   /* private List<OrderItem> orderItemList;

    private List<Comment> commentList;
*/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatId() {
        return createId;
    }

    public void setCreatId(Integer creatId) {
        this.createId = creatId;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
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

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    /*public List<OrderItem> getOrderDetailList() {
        return orderItemList;
    }

    public void setOrderDetailList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }*/

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

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    /*public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
*/
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", createId=" + createId +
                ", updateId=" + updateId +
                ", storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", productCount=" + productCount +
                ", saleCount=" + saleCount +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
