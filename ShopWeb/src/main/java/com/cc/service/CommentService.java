package com.cc.service;

import com.cc.po.Comment;

import java.util.List;

public interface CommentService {
    /**
     * 查询某一商品的所有评论
     * @return
     */
    List<Comment> getAllByProductId(Integer productId) throws Exception;

    /**
     * 添加评论
     */
    void add(Comment comment) throws Exception;

    /**
     * 删除评论
     */
    void delete(Integer id) throws Exception;
}
