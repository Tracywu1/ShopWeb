package com.cc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MyDataSource{
    /**
     * 当前连接数
     */
    private static int currConn;
    /**
     * 连接池中的连接
     */
    private static List<Connection> connList;

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    public static synchronized Connection getConnection(String url, String username,String password,int maxConn) throws SQLException {
        Connection conn = null;
        if (!connList.isEmpty()) {
            conn = connList.remove(0);
        } else {
            if (currConn < maxConn) {
                conn = DriverManager.getConnection(url, username, password);
                currConn++;
            } else {
                throw new SQLException("连接池已满");
            }
        }
        return conn;
    }

    /**
     * 释放连接
     * @param conn
     */
    public static synchronized void releaseConnection(Connection conn) {
        if (conn != null) {
            connList.add(conn);
        }
    }

    /**
     * 关闭连接池
     * @throws SQLException
     */
    public static synchronized void close() throws SQLException {
        for (Connection conn : connList) {
            conn.close();
        }
        connList.clear();
        currConn = 0;
    }

    public static synchronized void init(String url, String username,String password,int initConn) throws SQLException {
        for (int i = 0; i < initConn; i++) {
            Connection conn = DriverManager.getConnection(url, username, password);
            connList.add(conn);
            currConn++;
        }
    }

    public static int getCurrConn() {
        return currConn;
    }

    public static List<Connection> getConnList() {
        return connList;
    }
}
