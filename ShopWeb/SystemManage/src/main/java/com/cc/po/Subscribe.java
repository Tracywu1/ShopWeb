package com.cc.po;

import java.util.Date;

public class Subscribe {
    /**
     * 关注ID
     */
    private Integer id;
    /**
     * 用户
     */
    private User user;
    /**
     * 店铺
     */
    private Store store;
    /**
     * 关注时间
     */
    private Date createTime;

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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
