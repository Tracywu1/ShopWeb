package com.cc.po;

import java.util.List;

public class User {
    /**
     *用户ID
     */
    private Integer id;
    /**
     *用户名
     */
    private String username;
    /**
     *昵称
     */
    private String nickname;
    /**
     *密码
     */
    private String password;
    /**
     *地址
     */
    private String address;
    /**
     *电话号码
     */
    private String phoneNum;
    /**
     *头像地址
     */
    private String image;

    private List<Address> addressList;

    private List<Chat> chatList;

    private List<Report> reportList;

    private List<Message> messageList;

    private List<Cart> cartList;

    private List<Order> orderList;

    private List<Return> returnList;

    private List<Comment> commentList;

    private List<Subscribe> subscribeList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }

    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Return> getReturnList() {
        return returnList;
    }

    public void setReturnList(List<Return> returnList) {
        this.returnList = returnList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Subscribe> getSubscribeList() {
        return subscribeList;
    }

    public void setSubscribeList(List<Subscribe> subscribeList) {
        this.subscribeList = subscribeList;
    }
}
