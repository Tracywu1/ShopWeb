package com.cc.service.Impl;

import com.cc.dao.ChatDao;
import com.cc.dao.Impl.ChatDaoImpl;
import com.cc.dao.Impl.UserDaoImpl;
import com.cc.dao.UserDao;
import com.cc.po.Chat;
import com.cc.service.ChatService;

import java.util.List;

public class ChatServiceImpl implements ChatService {
    private final ChatDao chatDao = new ChatDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    @Override
    public void insertSelective(Chat chat) throws Exception {
        chat.setFromUsername(userDao.selectById(chat.getFromUserId()).getUsername());
        chat.setToUsername(userDao.selectById(chat.getToUserId()).getUsername());
        chatDao.insertSelective(chat);
    }

    @Override
    public List<Chat> selectByFromUserIdAndToUserId(Integer fromUserId, Integer toUserId) throws Exception {
        return chatDao.selectByFromUserIdAndToUserId(fromUserId,toUserId);
    }
}
