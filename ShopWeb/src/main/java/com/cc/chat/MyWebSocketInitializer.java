package com.cc.chat;

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
import java.util.Set;

/*
public class MyWebSocketInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 获取 ServletContext 对象
        ServletContext servletContext = sce.getServletContext();

        // 注册 MyWebSocketConfig 配置类
        ServerApplicationConfig serverApplicationConfig = new MyWebSocketConfig();
        Set<ServerEndpointConfig> configs = serverApplicationConfig.getEndpointConfigs(null);
        ServerEndpointConfig config = configs.iterator().next();
        ServerContainer serverContainer = (ServerContainer) servletContext.getAttribute("javax.websocket.server.ServerContainer");
        try {
            serverContainer.addEndpoint(config);
        } catch (DeploymentException e) {
            throw new RuntimeException(e);
        }

        // 启动 WebSocket
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            container.connectToServer(ChatEndpoint.class, URI.create("ws://localhost:8080/chat"));
        } catch (DeploymentException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 在这里可以关闭 WebSocket
    }

}*/
