package com.cc.service.Impl;

import com.cc.service.ChatService;
import com.cc.utils.ChatServer;

/**
 * @author 32119
 */

public class ChatServiceImpl implements ChatService {
    private ChatServer chatServer;

    @Override
    public void start(int port) {
        chatServer = new ChatServer();
        chatServer.start(port);
    }

    @Override
    public void stop() {
        chatServer.broadcast("服务器已关闭，聊天室将于10秒后自动关闭。", null);
        chatServer.stop();
    }

    @Override
    public void broadcast(String message) {
        chatServer.broadcast(message, null);
    }
}



