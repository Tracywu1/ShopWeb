package com.cc.controller;


import com.cc.filter.LoginCheckFilter;
import com.cc.po.Message;
import com.cc.utils.MessageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat")
public class ChatEndpoint {

    //用来存储每个用户客户端对象的ChatEndpoint对象
    private static Map<String,ChatEndpoint> onlineUsers = new ConcurrentHashMap<>();

    //声明session对象，通过对象可以发送消息给指定的用户
    private Session session;


    //连接建立
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        this.session = session;
        //存储登陆的对象
        String username = LoginCheckFilter.currentUser.getUsername();
        onlineUsers.put(username,this);

        //将当前在线用户的用户名推送给所有的客户端
        //1 获取消息
        String message = MessageUtils.getMessage(true, null, getNames());
        //2 调用方法进行系统消息的推送
        broadcastAllUsers(message);
    }

    private void broadcastAllUsers(String message){
        try {
            //将消息推送给所有的客户端
            Set<String> names = onlineUsers.keySet();
            for (String name : names) {
                ChatEndpoint chatEndpoint = onlineUsers.get(name);
                chatEndpoint.session.getBasicRemote().sendText(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //返回在线用户名
    private Set<String> getNames(){
        return onlineUsers.keySet();
    }

    //收到消息
    @OnMessage
    public void onMessage(String message,Session session){
        //将数据转换成对象
        try {
            ObjectMapper mapper =new ObjectMapper();
            Message mess = mapper.readValue(message, Message.class);
            String toName = mess.getToName();
            String data = mess.getMessage();
            String username = LoginCheckFilter.currentUser.getUsername();
            String resultMessage = MessageUtils.getMessage(false, username, data);
            //发送数据
            onlineUsers.get(toName).session.getBasicRemote().sendText(resultMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //关闭
    @OnClose
    public void onClose(Session session) {
        String username = LoginCheckFilter.currentUser.getUsername();
        //从容器中删除指定的用户
        onlineUsers.remove(username);
        MessageUtils.getMessage(true,null,getNames());
    }
}

