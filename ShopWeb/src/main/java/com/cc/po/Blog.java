package com.cc.po;

import java.sql.Timestamp;
import java.util.List;

public class Blog {
    /**
     * 动态ID
     */
    private Integer id;
    /**
     * 所属商品ID
     */
    private Integer productId;
    /**
     * 所属店铺ID
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

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", productId=" + productId +
                ", store=" + store +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
