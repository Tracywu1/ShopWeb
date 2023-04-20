package com.cc.controller;

import com.cc.utils.MyDataSource;

import java.sql.SQLException;

public class ConnectionPoolListener {

    /**
     * 数据库连接池
     */
    private MyDataSource dataSource;
    /**
     * 超时时间
     */
    private long timeout=600;
    /**
     * 上一次 JDBCUtils 使用的时间
     */
    private long lastUsedTime;

    private ConnectionPoolListener(MyDataSource dataSource, long timeout) {
        this.dataSource = dataSource;
        this.timeout = timeout;
        this.lastUsedTime = System.currentTimeMillis();
    }

    /**
     * JDBCUtils 使用之前调用此方法，更新 lastUsedTime 的值
     */
    public void beforeExecute() {
        lastUsedTime = System.currentTimeMillis();
    }

    /**
     * 检查是否超时并关闭数据库连接池
     */
    public void checkTimeoutAndClose() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUsedTime > timeout) {
            try {
                // 关闭数据库连接池
                MyDataSource.close();
                System.out.println("数据库连接池已关闭");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
