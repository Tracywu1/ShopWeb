package com.cc.po;

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
}
