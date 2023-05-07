package com.cc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.*;

public class MyConnectionPool {
    private final String driver;
    private final String url;
    private final String username;
    private final String password;
    private final int maxConnections;
    private final BlockingQueue<Connection> availableConnections;
    private final Set<Connection> usedConnections;
    private final ScheduledExecutorService reclaimerExecutor;
    private final ScheduledExecutorService keeperExecutor;

    public MyConnectionPool(String driver, String url, String username, String password, int maxConnections) throws SQLException {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
        this.maxConnections = maxConnections;
        this.availableConnections = new ArrayBlockingQueue<>(maxConnections);
        this.usedConnections = new HashSet<>();
        this.reclaimerExecutor = Executors.newSingleThreadScheduledExecutor();
        this.keeperExecutor = Executors.newSingleThreadScheduledExecutor();
        initializePool();
        startReclaimer();
        startKeeper();
    }

    private void initializePool() throws SQLException {
        for (int i = 0; i < maxConnections; i++) {
            Connection connection = null;
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
                availableConnections.put(connection);
            } catch (ClassNotFoundException | SQLException | InterruptedException e) {
                if (connection != null) {
                    connection.close();
                }
                throw new SQLException("数据库连接池初始化失败", e);
            }
        }
    }

    public Connection getConnection() throws SQLException {
        Connection connection = availableConnections.poll();
        if (connection == null) {
            throw new SQLException("数据库连接池已达到最大连接数");
        }
        if (!connection.isValid(5)) {
            connection = DriverManager.getConnection(url, username, password);
        }
        usedConnections.add(connection);
        return connection;
    }

    public void releaseConnection(Connection connection) throws SQLException {
        if (connection == null || !usedConnections.remove(connection)) {
            throw new SQLException("该连接无效");
        }
        availableConnections.offer(connection);
    }

    private void startReclaimer() {
        reclaimerExecutor.scheduleAtFixedRate(() -> {
            List<Connection> connectionsToRemove = new ArrayList<>();
            for (Connection connection : availableConnections) {
                try {
                    if (!connection.isValid(5)) {
                        connection.close();
                        connectionsToRemove.add(connection);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            availableConnections.removeAll(connectionsToRemove);
        }, 5, 5, TimeUnit.MINUTES);
    }

    private void startKeeper() {
        keeperExecutor.scheduleAtFixedRate(() -> {
            for (Connection connection : availableConnections) {
                try {
                    connection.isValid(5);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, 5, 5, TimeUnit.MINUTES);
    }

    public void shutdown() {
        reclaimerExecutor.shutdownNow();
        keeperExecutor.shutdownNow();
        for (Connection connection : availableConnections) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        availableConnections.clear();
        usedConnections.clear();
    }
}