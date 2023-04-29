package com.cc.dao;

import com.cc.po.Comment;

import java.util.List;

public interface CommentDao {
    /**
     * 根据商品ID查询其评论信息
     * @param productId
     * @return
     */
    List<Comment> selectByProductId(Integer productId) throws Exception;

    /**
     * 删除评论
     * @param id
     */
    void delete(Integer id) throws Exception;

    /**
     * 发布评论
     * @param comment
     */
    void insertSelective(Comment comment) throws Exception;
}
