package com.cc.po;

import java.sql.Timestamp;
import java.util.List;

public class Blog {
    /**
     * 动态ID
     */
    private Integer id;
    /**
     * 所属商品
     */
    private Product product;
    /**
     * 所属店铺
     */
    private Store store;
    /**
     * 动态内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Timestamp  createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
    /**
     * 一个动态可对应多个商品
     */
    private List<Product> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", product=" + product +
                ", store=" + store +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", products=" + products +
                '}';
    }
}
