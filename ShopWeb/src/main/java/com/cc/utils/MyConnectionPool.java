/*
package com.cc.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyConnectionPool{
    static{
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                release();
            }
        },0,1000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                send();
            }
        },0,1000*10L);
    }

    public static String Driver;
    public static String url;
    public static String username;
    public static String psd;
    //利用CopyOnWriteArrayList存放MyConnection,初始化池
    public  static CopyOnWriteArrayList<MyConnection> myConnections=new CopyOnWriteArrayList<MyConnection>();
    //初始化池内连接数量
    public static int count=0;

    //读取jdbc配置文件
    private static void readPro(){
        try{
            Properties pro=new Properties();
            InputStream in= new FileInputStream("src/main/resources/db.properties");
            pro.load(in);
            Driver=pro.getProperty("Driver");
            url=pro.getProperty("url");
            username=pro.getProperty("username");
            psd=pro.getProperty("psd");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //在池中获取连接
    public static synchronized Connection getConnection(){
        for(int i=0;i<count;i++){      //若池内有空闲的连接则连上
            if(!myConnections.get(i).isUsed){
                myConnections.get(i).isUsed=true;
                System.out.println("我空闲我连上");
                return myConnections.get(i);
            };
        }
        //若池内连接数超过10个连接（可自定义池内最大连接数），则提示系统繁忙
        if(count>=10){
            System.out.println("系统繁忙！");
            return null;
        }
        //若池内没有连接空闲，且连接数不超过池可以承载的最大数量，则创建一个新连接
        readPro();
        Connection conn=null;
        try{
            //--1，加载Driver驱动--
            Class.forName(Driver);
            //--2，创建数据库连接对象Connection--
            conn = DriverManager.getConnection(url, username, psd);
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        MyConnection myconnection=new MyConnection(conn);
        //标记连接为繁忙
        myconnection.isUsed=true;
        //将其加入池中
        myConnections.add(myconnection);
        System.out.println("我新建一个");
        count++; //池内连接数量+1
        return myconnection;
    }
    public static  void release(){
        int count=0;
        for(MyConnection myConnection:MyConnectionPool.myConnections){
            if (!myConnection.isUsed){ //若连接池有6个空闲，则关闭其他空闲
                if(count>=6){
                    try {
                        myConnection.realclose();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                count++;
            }
        }
    }
    public static  void send(){
        Statement stmt = null;
        String sql="SELECT * FROM account";
        */
/**
         * 定时发送sql语句确保连接
         *//*

        for(MyConnection myConnection:MyConnectionPool.myConnections){
            if(myConnection.isUsed) {
                continue;
            }
            try {
                stmt = myConnection.createStatement();
                stmt.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                if (stmt!=null){
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if(myConnection!=null){
                    try {
                        myConnection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}
*/
