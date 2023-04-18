package com.cc.po;

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
     * 更新者ID
     */
    private User updater;
    /**
     * 商品ID
     */
    private Product product;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 发布时间
     */
    private String createTime;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
