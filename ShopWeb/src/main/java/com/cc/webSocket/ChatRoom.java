package com.cc.webSocket;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    private Integer storeId; // 与店铺ID进行关联
    private List<ChatEndpoint> connections;

    public ChatRoom(Integer storeId) {
        this.storeId = storeId;
        this.connections = new ArrayList<>();
    }
    public void join(ChatEndpoint connection) {
        connections.add(connection);
    }

    public void leave(ChatEndpoint connection) {
        connections.remove(connection);
    }

    public void broadcast(String message) {
        for (ChatEndpoint connection : connections) {
            try {
                connection.getSession().getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
