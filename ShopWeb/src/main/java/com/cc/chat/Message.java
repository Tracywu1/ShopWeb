package com.cc.chat;

public class Message {
    /**
     * 接收者id
     */
    private Integer toId;
    /**
     * 接收者名字
     */
    private String toName;
    /**
     * 发送的内容
     */
    private String message;

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "toId=" + toId +
                ", toName='" + toName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
