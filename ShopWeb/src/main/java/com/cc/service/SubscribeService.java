package com.cc.service;

import com.cc.po.Comment;
import com.cc.po.Subscribe;

import java.util.List;

public interface SubscribeService {
    /**
     * 查询某一用户的关注列表
     * @return
     */
    List<Subscribe> getAllByProductId(Integer userId) throws Exception;

    /**
     * 关注
     */
    void add(Integer storeId) throws Exception;

    /**
     * 取消关注
     */
    void delete(Integer id) throws Exception;
}
