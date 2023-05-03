package com.cc.dao.Impl;

import com.cc.dao.CommentDao;
import com.cc.dao.SubscribeDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.po.Comment;
import com.cc.po.Subscribe;
import com.cc.utils.CRUDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SubscribeDaoImpl implements SubscribeDao {
    private static final Logger logger = LoggerFactory.getLogger(SubscribeDaoImpl.class);

    @Override
    public List<Subscribe> selectByUserId(Integer userId) throws Exception {
        String sql ="select * from tb_subscribe where userId = ?";
        List<Subscribe> subscribeList = CRUDUtils.queryMore(sql,Subscribe.class,userId);
        logger.debug("subscribeList:"+subscribeList);
        return subscribeList;
    }

    @Override
    public void delete(Integer id) throws Exception {
        String sql ="delete from tb_subscribe where id = ?";
        int update = CRUDUtils.update(sql,id);

        if (update == 0) {
            throw new MyException(ResultCode.DELETE_FAILED);
        }
        
        logger.debug("update:"+update);
    }

    @Override
    public void insertSelective(Subscribe subscribe) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("insert into tb_subscribe");
        StringBuilder columnsBuilder = new StringBuilder("(");
        StringBuilder valuesBuilder = new StringBuilder("(");
        if (subscribe.getId() != null) {
            columnsBuilder.append("`id`,");
            valuesBuilder.append("?,");
        }
        if (subscribe.getUserId() != null) {
            columnsBuilder.append("`userId`,");
            valuesBuilder.append("?,");
        }
        if (subscribe.getStoreId() != null) {
            columnsBuilder.append("`storeId`,");
            valuesBuilder.append("?,");
        }
        if(subscribe.getStoreName()!= null){
            columnsBuilder.append("`storeName`,");
            valuesBuilder.append("?,");
        }
        if (subscribe.getCreateTime() != null) {
            columnsBuilder.append("`createTime`,");
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

        if (subscribe.getId() != null) {
            count++;
        }
        if (subscribe.getUserId() != null) {
            count++;
        }
        if (subscribe.getStoreId() != null) {
            count++;
        }
        if (subscribe.getStoreName() != null &&!subscribe.getStoreName().isEmpty()){
            count++;
        }
        if (subscribe.getCreateTime() != null) {
            count++;
        }

        Object[] params = new Object[count + 1];

        int index = 0;

        if (subscribe.getId() != null) {
            params[index] = subscribe.getId();
            index++;
        }
        if (subscribe.getUserId() != null) {
            params[index] = subscribe.getUserId();
            index++;
        }
        if (subscribe.getStoreId() != null) {
            params[index] = subscribe.getStoreId();
            index++;
        }
        if (subscribe.getStoreName() != null && !subscribe.getStoreName().isEmpty()){
            params[index] = subscribe.getStoreName();
            index++;
        }
        if (subscribe.getCreateTime() != null) {
            params[index] = subscribe.getCreateTime();
        }

        int update = CRUDUtils.update(sqlBuilder.toString(), params);

        if (update == 0) {
            throw new MyException(ResultCode.INSERT_FAILED);
        }

        logger.debug("update:" + update);
    }

    @Override
    public Integer selectFollowCountById(Integer userId) throws Exception {
        String sql = "select count(*) from tb_subscribe where userId = ?";
        int followCount = CRUDUtils.queryCount(sql, userId);
        logger.debug("followCount:" + followCount);
        return followCount;
    }
}
