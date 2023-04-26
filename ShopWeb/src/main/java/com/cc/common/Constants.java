package com.cc.common;

public class Constants {

    public static final String QG_MALL_USER = "qg_mall_user";

    public interface IsSelected {
      int  SELECTED =1;
      int  UN_SELECTED = 0;
    }

    public enum MsgType {
        /**
         * 系统消息
         */
        SYSTEM_MSG(1,"系统消息"),

        /**
         * 店铺动态消息
         */
        STORE_BLOG_MSG(2,"店铺动态提醒");

        private int num;

        private String type;

        MsgType(int num, String type) {
            this.num = num;
            this.type = type;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public enum OrderStatus {
        /**
         * 尚未支付
         */
        NOT_PAID(0,"尚未支付"),
        /**
         * 未发货
         */
        NOT_SHIPPED(1,"未发货"),
        /**
         * 已发货
         */
        DELIVERED(2,"已发货"),
        /**
         * 已收货
         */
        RECEIVED(3,"已收货"),
        /**
         * 售后
         */
        AFTER_SALES_SERVICE(4,"售后");

        private int num;

        private String status;

        OrderStatus(int num, String status) {
            this.num = num;
            this.status = status;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public static String fromValue(int num) {
            for (OrderStatus orderStatus : values()) {
                if (orderStatus.getNum() == num) {
                    return orderStatus.getStatus();
                }
            }
            throw new IllegalArgumentException("Invalid OrderStatus value: " + num);
        }
    }
    public enum ReportStatus {
        /**
         * 待处理
         */
        TO_BE_PROCESSED(1,"待处理"),
        /**
         * 已处理
         */
        PROCESSED(2,"已处理"),
        /**
         * 已驳回
         */
        REJECTED(3,"已驳回");

        private int num;

        private String status;

        ReportStatus(int num, String status) {
            this.num = num;
            this.status = status;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public enum ReturnStatus {
        /**
         * 待处理
         */
        TO_BE_PROCESSED(1,"待处理"),
        /**
         * 已处理
         */
        PROCESSED(2,"已处理"),
        /**
         * 已退款
         */
        REFUNDED(3,"已退款"),
        /**
         * 已取消
         */
        CANCELLED(4,"已取消");

        private int num;

        private String sstatus;

        ReturnStatus(int num, String sstatus) {
            this.num = num;
            this.sstatus = sstatus;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getSstatus() {
            return sstatus;
        }

        public void setSstatus(String sstatus) {
            this.sstatus = sstatus;
        }
    }

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

}
