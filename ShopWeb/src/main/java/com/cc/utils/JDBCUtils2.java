/*
package com.cc.utils;

import com.cc.dao.DBConfig;
import java.sql.*;

*/
/**
 * @author 32119
 *//*

public class JDBCUtils2 {
    */
/**
     * 获取可用的连接对象
     *
     * @return connection
     *//*

    public static Connection getConnection() throws SQLException {
        return MyDataSource.getConnection(DBConfig.getUrl(), DBConfig.getUsername(), DBConfig.getPassword(),DBConfig.getMaxConn());
    }

    */
/**
     * 释放连接资源
     *
     * @param conn 连接对象
     * @param psmt   命令对象
     * @param rs   结果集对象
     *//*

    public static void release(Connection conn,PreparedStatement psmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (psmt != null) {
            try {
                psmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            MyDataSource.releaseConnection(conn);
        }
    }

    private JDBCUtils2() {
    }
}
*/
