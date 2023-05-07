package com.cc.po;

import java.sql.Timestamp;

public class Comment {
    /**
     * 评论ID
     */
    private Integer id;
    /**
     * 发布者ID
     */
    private Integer creatorId;
    /**
     * 商品ID
     */
    private Integer productId;
    /**
     * 用户昵称
     */
    private String nickName;
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

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", creatorId=" + creatorId +
                ", productId=" + productId +
                ", nickName='" + nickName + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
