package com.cc.service;

import com.cc.po.Chat;

import java.util.List;

public interface ChatService {
    /**
     * 添加数据
     * @param chat
     */
    void insertSelective(Chat chat) throws Exception;

    /**
     * 查找聊天记录
     * @param fromUserId
     * @param toUserId
     * @return
     * @throws Exception
     */
    List<Chat> selectByFromUserIdAndToUserId(Integer fromUserId, Integer toUserId)throws Exception;

}
