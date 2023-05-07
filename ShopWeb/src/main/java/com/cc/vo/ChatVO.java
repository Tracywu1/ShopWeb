package com.cc.vo;

import java.sql.Timestamp;

public class ChatVO {
    private Integer fromUserId;

    private Integer toUserId;
    /**
     * 店铺头像
     */
    private String avatar;
    private String storeName;
    /**
     * 与某一店铺最近的一次对话内容
     */
    private String preview;
    /**
     * 最近的发送时间，for sort
     */
    private Timestamp lastSendTime;

    public String getAvatar() {
        return avatar;
    }

    public String getPreview() {
        return preview;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Timestamp getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(Timestamp lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    @Override
    public String toString() {
        return "ChatVO{" +
                "fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                ", avatar='" + avatar + '\'' +
                ", storeName='" + storeName + '\'' +
                ", preview='" + preview + '\'' +
                ", lastSendTime=" + lastSendTime +
                '}';
    }
}
