package com.cc.dao;

import com.cc.po.Subscribe;

import java.util.List;

public interface SubscribeDao {
    /**
     * 根据用户ID查询其关注列表
     * @param userId
     * @return
     */
    List<Subscribe> selectByUserId(Integer userId) throws Exception;

    /**
     * 删除数据
     * @param id
     */
    void delete(Integer id) throws Exception;

    /**
     * 添加数据
     * @param subscribe
     */
    void insertSelective(Subscribe subscribe) throws Exception;


}
