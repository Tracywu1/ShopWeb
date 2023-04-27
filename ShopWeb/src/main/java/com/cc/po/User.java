package com.cc.po;


import java.sql.Timestamp;
import java.util.List;

public class User {
    /**
     *用户ID
     */
    private Integer id;
    /**
     * 店铺的id（只有店铺管理员有，普通用户默认为0）
     */
    private Integer storeId;
    /**
     *用户名  （5-25位中英文、数字、下划线或组合）
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
     * 电子邮箱（注册）
     */
    private String email;
    /**
     *电话号码
     */
    private String phoneNum;
    /**
     *头像地址
     */
    private String image;
    /**
     * 用户角色
     */
    private Integer userRole;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
    private List<Address> addressList;

    private List<Chat> chatList;

    private List<Report> reportList;

    private List<Message> messageList;

    private List<Cart> cartList;

    private List<Order> orderList;

    private List<ReturnApplication> returnApplicationList;

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

    public List<ReturnApplication> getReturnList() {
        return returnApplicationList;
    }

    public void setReturnList(List<ReturnApplication> returnApplicationList) {
        this.returnApplicationList = returnApplicationList;
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

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", storeId=" + storeId +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", image='" + image + '\'' +
                ", userRole=" + userRole +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", addressList=" + addressList +
                ", chatList=" + chatList +
                ", reportList=" + reportList +
                ", messageList=" + messageList +
                ", cartList=" + cartList +
                ", orderList=" + orderList +
                ", returnApplicationList=" + returnApplicationList +
                ", commentList=" + commentList +
                ", subscribeList=" + subscribeList +
                '}';
    }
}
