package com.cc.chat;

import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class StoreSocket {

    // 该 StoreSocket 对象所属的店铺 ID
    private String storeId;

    // 存储该店铺中所有连接该 StoreSocket 的客户端实例
    private List<Session> sessions = new ArrayList<>();

    // 加入新的客户端实例到该 StoreSocket 的 sessions 列表中
    public void join(Session session) {
        sessions.add(session);
    }

    // 将客户端实例从该 StoreSocket 的 sessions 列表中移除
    public void leave(Session session) {
        sessions.remove(session);
    }

    // 向该 StoreSocket 的所有客户端实例发送消息
    public void broadcast(String message) {
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 根据客户端 ID 获取客户端实例
    public Session getSession(String clientId) {
        for (Session session : sessions) {
            if (session.getUserProperties().get("clientId").equals(clientId)) {
                return session;
            }
        }
        return null;
    }
}
