package com.cc.service.Impl;

import com.cc.dao.ChatDao;
import com.cc.dao.Impl.ChatDaoImpl;
import com.cc.dao.Impl.StoreDaoImpl;
import com.cc.dao.Impl.UserDaoImpl;
import com.cc.dao.StoreDao;
import com.cc.dao.UserDao;
import com.cc.po.Chat;
import com.cc.service.ChatService;
import com.cc.vo.ChatVO;

import java.util.*;

public class ChatServiceImpl implements ChatService {
    private final ChatDao chatDao = new ChatDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    private final StoreDao storeDao = new StoreDaoImpl();

    @Override
    public void insertSelective(Chat chat) throws Exception {
        chat.setFromUsername(userDao.selectById(chat.getFromUserId()).getUsername());
        chat.setToUsername(userDao.selectById(chat.getToUserId()).getUsername());
        chatDao.insertSelective(chat);
    }

    @Override
    public List<Chat> selectByFromUserIdAndToUserId(Integer fromUserId, Integer toUserId) throws Exception {
        return chatDao.selectByFromUserIdAndToUserId(fromUserId, toUserId);
    }

    @Override
    public List<ChatVO> list(Integer userId) throws Exception {
        List<Chat> chatList = chatDao.selectByUserId(userId);

        // Creating a map of chat partner's user ID to the latest chat object
        Map<Integer, Chat> latestChatsMap = new HashMap<>();
        for (Chat chat : chatList) {
            int chatPartnerId;
            if (Objects.equals(chat.getFromUserId(), userId)) {
                chatPartnerId = chat.getToUserId();
            } else if (Objects.equals(chat.getToUserId(), userId)) {
                chatPartnerId = chat.getFromUserId();
            } else {
                continue; // Skip chats that don't involve the current user
            }

            if (!latestChatsMap.containsKey(chatPartnerId) || chat.getSendTime().compareTo(latestChatsMap.get(chatPartnerId).getSendTime()) > 0) {
                latestChatsMap.put(chatPartnerId, chat);
            }
        }

        // Creating a list of ChatVO objects based on the latest chats
        List<ChatVO> chatVOList = new ArrayList<>();
        for (Chat chat : latestChatsMap.values()) {
            ChatVO chatVO = new ChatVO();
            if(!Objects.equals(chat.getToUserId(), userId)){
                chatVO.setAvatar(storeDao.selectStoreById(userDao.selectById(chat.getToUserId()).getStoreId()).getLogo());
                chatVO.setStoreName(storeDao.selectStoreById(userDao.selectById(chat.getToUserId()).getStoreId()).getStoreName());
            }
            if(!Objects.equals(chat.getFromUserId(), userId)){
                chatVO.setAvatar(storeDao.selectStoreById(userDao.selectById(chat.getFromUserId()).getStoreId()).getLogo());
                chatVO.setStoreName(storeDao.selectStoreById(userDao.selectById(chat.getFromUserId()).getStoreId()).getStoreName());
            }
            chatVO.setToUserId(chat.getToUserId());
            chatVO.setFromUserId(chat.getFromUserId());
            chatVO.setLastSendTime(chat.getSendTime());
            chatVO.setPreview(chat.getMessage());
            chatVOList.add(chatVO);
        }

        // Sorting the chatVOList based on the lastSendTime
        chatVOList.sort(Comparator.comparing(ChatVO::getLastSendTime).reversed());
        
        return chatVOList;
    }
}
