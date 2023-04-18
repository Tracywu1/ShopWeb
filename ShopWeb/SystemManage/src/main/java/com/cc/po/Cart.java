package com.cc.po;

import java.util.List;

public class Cart {
    /**
     * 购物车ID
     */
    private Integer id;
    /**
     * 所属用户
     */
    private User user;
    /**
     * 商品
     */
    private Product product;
    /**
     * 商品所属店铺
     */
    private Store store;
    /**
     * 购买数量
     */
    private Integer count;
    /**
     *商品单价
     */
    private Double price;
    /**
     *总价
     */
    private Double totalPrice;

    private List<Product> productList;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
