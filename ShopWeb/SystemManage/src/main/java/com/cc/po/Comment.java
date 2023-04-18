package com.cc.po;

public class Comment {
    /**
     * 评论ID
     */
    private Integer id;
    /**
     * 创建者ID
     */
    private Integer createId;
    /**
     * 更新者ID
     */
    private Integer updateId;
    /**
     * 商品ID
     */
    private Integer productId;
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

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
