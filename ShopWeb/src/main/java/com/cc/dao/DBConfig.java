/*
package com.cc.dao;

import com.cc.utils.MyDataSource;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DBConfig {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;
    private static int initConn = 0;
    private static int maxConn = 0;

    static {
        try {
            InputStream in = new FileInputStream("src/main/resources/db.properties");
            Properties properties = new Properties();
            properties.load(in);

            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            initConn= Integer.parseInt(properties.getProperty("initConn"));
            maxConn = Integer.parseInt(properties.getProperty("maxConn"));

            Class.forName(driver);
            MyDataSource.init(url,username,password,initConn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 定义私有静态成员变量，直接创建DBConfig实例
    private static DBConfig instance = new DBConfig();

    // 定义私有构造方法，避免外部创建实例
    private DBConfig() {
    }

    // 提供全局访问点，返回DBConfig实例
    public static DBConfig getInstance() {
        return instance;
    }

   //提供获得各类基本信息的方法
    public static String getDriver() {
        return driver;
    }

    public static String getUrl() {
        return url;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static int getInitConn() {
        return initConn;
    }

    public static int getMaxConn() {
        return maxConn;
    }
}


*/
