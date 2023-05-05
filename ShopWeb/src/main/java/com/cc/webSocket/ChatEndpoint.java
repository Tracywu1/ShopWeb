package com.cc.webSocket;


import com.cc.filter.LoginCheckFilter;
import com.cc.po.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat/")
public class ChatEndpoint {

    // 存储店铺 ID 与对应的聊天室
    private static Map<Integer, ChatRoom> storeChatRooms = new ConcurrentHashMap<>();

    // 存储客服 ID 与对应的 WebSocket 连接
    private static Map<Integer, ChatEndpoint> customerServiceConnections = new ConcurrentHashMap<>();

    // 存储用户 ID 与对应的 WebSocket 连接
    private static Map<Integer, ChatEndpoint> userConnections = new ConcurrentHashMap<>();

    // 当前 WebSocket 连接的 session 对象
    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    // 当前连接的用户类型，包括 customerService（客服）和 user（用户）
    private String userType;

    // 当前连接的用户 ID，包括客服 ID 和用户 ID
    private Integer userId;

    // 连接建立
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;

        // 获取用户类型和用户 ID
        Map<String, List<String>> params = session.getRequestParameterMap();
        userType = params.get("userType").get(0);
        userId = Integer.valueOf(params.get("userId").get(0));

        // 根据用户类型添加连接到相应的 Map 中
        if ("customerService".equals(userType)) {
            customerServiceConnections.put(userId, this);
        } else if ("user".equals(userType)) {
            userConnections.put(userId, this);

            // 获取店铺 ID
            Integer storeId = Integer.valueOf(params.get("storeId").get(0));

            // 获取对应的聊天室
            ChatRoom chatRoom = storeChatRooms.get(storeId);
            if (chatRoom == null) {
                // 如果聊天室不存在，创建新的聊天室
                chatRoom = new ChatRoom(storeId);
                storeChatRooms.put(storeId, chatRoom);
            }

            // 将当前连接加入到聊天室中
            chatRoom.join(this);
        }
    }

    // 收到消息
    @OnMessage
    public void onMessage(String message, Session session) {
        // 解析消息内容
        try {
            ObjectMapper mapper =new ObjectMapper();
            Message mess = mapper.readValue(message, Message.class);
            Integer toUserId = mess.getToId();
            String data = mess.getMessage();
            String username = LoginCheckFilter.currentUser.getUsername();
            String resultMessage = MessageUtils.getMessage(false, username, data);

            // 将消息转发给指定用户
            if ("customerService".equals(userType)) {
                // 客服将消息转发给用户
                userConnections.get(toUserId).session.getBasicRemote().sendText(resultMessage);
            } else if ("user".equals(userType)) {
                // 用户将消息转发给客服
                customerServiceConnections.get(toUserId).session.getBasicRemote().sendText(resultMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 连接关闭
    @OnClose
    public void onClose(Session session) {
        if ("customerService".equals(userType)) {
            // 如果是客服，从客服 Map 中移除当前连接
            customerServiceConnections.remove(userId);
        } else if ("user".equals(userType)) {
            // 如果是用户，从聊天室中移除当前连接
            String storeId = session.getUserProperties().get("storeId").toString();
            ChatRoom chatRoom = storeChatRooms.get(storeId);
            chatRoom.leave(this);
            // 从用户 Map 中移除当前连接
            userConnections.remove(userId);
        }
    }
}



