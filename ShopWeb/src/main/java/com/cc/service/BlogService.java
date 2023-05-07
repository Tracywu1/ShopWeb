package com.cc.service;

import com.cc.po.Blog;

import java.util.List;

public interface BlogService {
    /**
     * 查询某一店铺的所有动态
     * @return
     */
    List<Blog> getAllByStoreId(Integer storeId) throws Exception;

    /**
     * 发布动态
     */
    void add(Blog blog) throws Exception;

    /**
     * 删除动态
     */
    void delete(Integer id) throws Exception;
}
