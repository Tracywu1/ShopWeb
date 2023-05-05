package com.cc.dao.Impl;

import com.cc.dao.ChatDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.po.Chat;
import com.cc.utils.CRUDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ChatDaoImpl implements ChatDao {
    private static final Logger logger = LoggerFactory.getLogger(ChatDaoImpl.class);
    @Override
    public void insertSelective(Chat chat) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("insert into tb_chat");
        StringBuilder columnsBuilder = new StringBuilder("(");
        StringBuilder valuesBuilder = new StringBuilder("(");
        if (chat.getFromUserId() != null) {
            columnsBuilder.append("`fromUserId`,");
            valuesBuilder.append("?,");
        }
        if (chat.getFromUsername() != null) {
            columnsBuilder.append("`fromUsername`,");
            valuesBuilder.append("?,");
        }
        if (chat.getToUserId() != null) {
            columnsBuilder.append("`toUserId`,");
            valuesBuilder.append("?,");
        }
        if (chat.getToUsername() != null) {
            columnsBuilder.append("`toUsername`,");
            valuesBuilder.append("?,");
        }
        if (chat.getSendTime() != null) {
            columnsBuilder.append("`sendTime`,");
            valuesBuilder.append("?,");
        }


        //删掉最后一个逗号
        columnsBuilder.deleteCharAt(columnsBuilder.length() - 1);
        valuesBuilder.deleteCharAt(valuesBuilder.length() - 1);

        columnsBuilder.append(")");
        valuesBuilder.append(")");

        sqlBuilder.append(columnsBuilder);
        sqlBuilder.append(" ");
        sqlBuilder.append("values");
        sqlBuilder.append(" ");
        sqlBuilder.append(valuesBuilder);

        int count = 0;

        if (chat.getFromUserId() != null) {
            count++;
        }
        if (chat.getFromUsername() != null && !chat.getFromUsername().isEmpty()) {
            count++;
        }
        if (chat.getToUserId() != null) {
            count++;
        }
        if (chat.getToUsername() != null && !chat.getToUsername().isEmpty()) {
            count++;
        }
        if (chat.getSendTime() != null) {
            count++;
        }

        Object[] params = new Object[count + 1];

        int index = 0;

        if (chat.getFromUserId() != null) {
            params[index] = chat.getFromUserId();
            index++;
        }
        if (chat.getFromUsername() != null && !chat.getFromUsername().isEmpty()) {
            params[index] = chat.getFromUsername();
            index++;
        }
        if (chat.getToUserId() != null) {
            params[index] = chat.getToUserId();
            index++;
        }
        if (chat.getToUsername() != null && !chat.getToUsername().isEmpty()) {
            params[index] = chat.getToUsername();
            index++;
        }
        if (chat.getSendTime() != null) {
            params[index] = chat.getSendTime();
        }

        int update = CRUDUtils.update(sqlBuilder.toString(), params);

        if (update == 0) {
            throw new MyException(ResultCode.INSERT_FAILED);
        }

        logger.debug("update:" + update);
    }

    @Override
    public List<Chat> selectByFromUserIdAndToUserId(Integer fromUserId, Integer toUserId) throws Exception {
        Object[] params1 = {fromUserId,toUserId};
        String sql1 ="select * from tb_chat where fromUserId = ? and toUserId = ?";
        List<Chat> chatList1 = CRUDUtils.queryMore(sql1,Chat.class,params1);
        logger.debug("chatList1:"+chatList1);
        Object[] params2 = {toUserId,fromUserId};
        String sql2 ="select * from tb_chat where fromUserId = ? and toUserId = ?";
        List<Chat> chatList2 = CRUDUtils.queryMore(sql2,Chat.class,params2);
        logger.debug("chatList2:"+chatList2);
        List<Chat> chatList = new ArrayList<>();
        chatList.addAll(chatList1);
        chatList.addAll(chatList2);

        //按发送时间的先后排序
        chatList.sort(Comparator.comparing(Chat::getSendTime));

        return chatList;
    }
}
