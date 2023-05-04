package com.cc.vo;

import java.math.BigDecimal;

/**
 * @author 32119
 */
public class CartVO {

    private Integer id;

    private Integer productId;

    private Integer userId;

    private Integer count;

    private BigDecimal price;

    private BigDecimal totalPrice;

    private String productName;

    private String productImage;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    @Override
    public String toString() {
        return "CartVO{" +
                "id=" + id +
                ", productId=" + productId +
                ", userId=" + userId +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", productName='" + productName + '\'' +
                ", productImage='" + productImage + '\'' +
                '}';
    }
}
