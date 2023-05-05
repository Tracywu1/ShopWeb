package com.cc.chat;

import com.cc.po.Chat;
import com.cc.service.ChatService;
import com.cc.service.Impl.ChatServiceImpl;
import com.cc.service.Impl.UserServiceImpl;
import com.cc.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat/")
public class ChatEndpoint {
    private final UserService userService = new UserServiceImpl();
    private final ChatService chatService = new ChatServiceImpl();

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
    private Integer userType;

    // 当前连接的用户 ID，包括客服 ID 和用户 ID
    private Integer userId;

    // 连接建立
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws Exception {
        this.session = session;

        // 获取用户类型和用户 ID
        Map<String, List<String>> params = session.getRequestParameterMap();
        userType = Integer.valueOf(params.get("userRole").get(0));
        userId = Integer.valueOf(params.get("userId").get(0));
        Integer toUserId = Integer.valueOf(params.get("toUserId").get(0));

        // 根据用户类型添加连接到相应的 Map 中
        if (userType == 3) {
            customerServiceConnections.put(userId, this);
        } else if (userType == 1) {
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

            List<Chat> chats = null;
            if (userType == 3) {
                // 获取客服与用户之间的聊天记录
                chats = chatService.selectByFromUserIdAndToUserId(userId, toUserId);
            } else if (userType == 1) {
                // 获取用户与客服之间的聊天记录
                chats = chatService.selectByFromUserIdAndToUserId(userId, toUserId);
            }

            // 发送聊天记录给客户端
            for (Chat message : chats) {
                String resultMessage = MessageUtils.getMessage(false,userService.getById(userId).getUsername(), message.getMessage());
                session.getBasicRemote().sendText(resultMessage);
            }
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
            String username = userService.getById(userId).getUsername();
            String resultMessage = MessageUtils.getMessage(false, username, data);

            // 将消息转发给指定用户
            if (userType == 3) {
                // 客服将消息转发给用户
                userConnections.get(toUserId).session.getBasicRemote().sendText(resultMessage);
                // 存储聊天记录
                saveChatRecord(userId, toUserId, resultMessage);
            } else if (userType == 1) {
                // 用户将消息转发给客服
                customerServiceConnections.get(toUserId).session.getBasicRemote().sendText(resultMessage);
                // 存储聊天记录
                saveChatRecord(userId, toUserId, resultMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 连接关闭
    @OnClose
    public void onClose(Session session) {
        if (userType == 3) {
            // 如果是客服，从客服 Map 中移除当前连接
            customerServiceConnections.remove(userId);
        } else if (userType == 1) {
            // 如果是用户，从聊天室中移除当前连接
            Integer storeId = (Integer) session.getUserProperties().get("storeId");
            ChatRoom chatRoom = storeChatRooms.get(storeId);
            chatRoom.leave(this);
            // 从用户 Map 中移除当前连接
            userConnections.remove(userId);
        }
    }

    // 存储聊天记录
    private void saveChatRecord(Integer fromUserId, Integer toUserId, String message) throws Exception {
        Chat chat = new Chat();
        chat.setFromUserId(fromUserId);
        chat.setToUserId(toUserId);
        chat.setMessage(message);

        // 调用聊天记录的Service来存储聊天记录
        chatService.insertSelective(chat);
    }

}