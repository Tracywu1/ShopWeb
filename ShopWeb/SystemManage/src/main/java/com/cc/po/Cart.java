package com.cc.po;

public class Cart {
    /**
     * 购物车ID
     */
    private Integer id;
    /**
     *用户ID
     */
    private Integer userId;
    /**
     *商品ID
     */
    private Integer productId;
    /**
     *店铺ID
     */
    private Integer storeId;
    /**
     *购买数量
     */
    private Integer count;
    /**
     *商品单价
     */
    private Float price;
    /**
     *总价
     */
    private Float totalPrice;

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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
