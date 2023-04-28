package com.cc.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author 32119
 */
public class JDBCUtils {
    private static MyConnectionPool connectionPool = null;

    static {
        try {
            InputStream in = new FileInputStream("D:\\qg\\最终考核\\购物网站\\ShopWeb\\src\\main\\resources\\db.properties");
            Properties properties = new Properties();
            properties.load(in);

            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            int maxConnections = Integer.parseInt(properties.getProperty("maxConnections"));

            connectionPool = new MyConnectionPool(driver, url, username, password, maxConnections);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取可用的连接对象
     *
     * @return connection
     */
    public static Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    /**
     * 释放连接资源
     *
     * @param conn 连接对象
     * @param st   命令对象
     * @param rs   结果集对象
     */
    public static void release(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                connectionPool.releaseConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
