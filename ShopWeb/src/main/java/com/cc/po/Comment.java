package com.cc.po;

import java.sql.Timestamp;
import java.util.Date;

public class Comment {
    /**
     * 评论ID
     */
    private Integer id;
    /**
     * 创建者
     */
    private User creator;
    /**
     * 商品
     */
    private Product product;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 发布时间
     */
    private Timestamp createTime;

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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", creator=" + creator +
                ", product=" + product +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
