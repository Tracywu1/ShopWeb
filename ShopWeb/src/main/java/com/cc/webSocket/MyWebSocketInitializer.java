package com.cc.webSocket;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;
import java.io.IOException;
import java.net.URI;

public class MyWebSocketInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 获取 ServletContext 对象
        ServletContext servletContext = sce.getServletContext();

        // 创建 WebSocketContainer 对象
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        ServerContainer serverContainer = (ServerContainer)container;

        // 注册 WebSocket 配置类
        ServerEndpointConfig chatEndpointConfig = ServerEndpointConfig.Builder.create(ChatEndpoint.class, "/chat").build();
        try {
            serverContainer.addEndpoint(chatEndpointConfig);
        } catch (DeploymentException e) {
            throw new RuntimeException(e);
        }

        // 注册 MyWebSocketConfig 配置类
        ServerApplicationConfig serverApplicationConfig = new MyWebSocketConfig();
        try {
            serverContainer.addEndpoint((ServerEndpointConfig) serverApplicationConfig.getEndpointConfigs(null));
        } catch (DeploymentException e) {
            throw new RuntimeException(e);
        }

        // 启动 WebSocket
        try {
            container.connectToServer(ChatEndpoint.class, URI.create("ws://localhost:8080/myEndpoint"));
        } catch (DeploymentException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 在这里可以关闭 WebSocket
    }

}
