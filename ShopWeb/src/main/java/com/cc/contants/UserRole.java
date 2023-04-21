package com.cc.contants;

public enum UserRole {
    /**
     * 普通用户
     */
    ORDINARY_USERS(1,"普通用户"),
    /**
     * 店铺管理员
     */
    STORE_MANAGER(2,"店铺管理员"),
    /**
     * 网站管理员
     */
    WEBMASTER(3,"网站管理员");

    private int num;

    private String role;

    UserRole(int num, String role) {
        this.num = num;
        this.role = role;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
