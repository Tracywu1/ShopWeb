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
     * 根据id查询
     * @param id
     * @return
     * @throws Exception
     */
    Subscribe getById(Integer id)throws Exception;

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

    /**
     * 查询关注的用户的数量
     * @param userId
     * @return
     * @throws Exception
     */
    Integer selectFollowCountById(Integer userId)throws Exception;


}
