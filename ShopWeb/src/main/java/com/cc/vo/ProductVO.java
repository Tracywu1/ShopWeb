package com.cc.vo;

import com.cc.po.Comment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class ProductVO {
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
     * 库存
     */
    private Integer productCount;
    /**
     * 总销售量
     */
    private Integer saleCount;
    /**
     * 月销售量
     */
    private Integer monthlySaleCount;
    /**
     * 评论
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

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
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

    public Integer getMonthlySaleCount() {
        return monthlySaleCount;
    }

    public void setMonthlySaleCount(Integer monthlySaleCount) {
        this.monthlySaleCount = monthlySaleCount;
    }

    @Override
    public String toString() {
        return "ProductVO{" +
                "id=" + id +
                ", storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", productCount=" + productCount +
                ", saleCount=" + saleCount +
                ", monthlySaleCount=" + monthlySaleCount +
                ", commentList=" + commentList +
                '}';
    }
}
