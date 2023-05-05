package com.cc.chat;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MyWebSocketConfig implements ServerApplicationConfig {

    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> endpointClasses) {
        Set<ServerEndpointConfig> result = new HashSet<>();

        // 在这里添加所有需要暴露的 Endpoint
        result.add(ServerEndpointConfig.Builder.create(ChatEndpoint.class, "/chat").build());

        return result;
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        // 如果需要使用注解方式配置 Endpoint，可以在这里添加扫描的包路径
        return Collections.emptySet();
    }

}