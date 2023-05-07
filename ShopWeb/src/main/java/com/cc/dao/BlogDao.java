package com.cc.dao;

import com.cc.po.Blog;
import com.cc.po.Subscribe;

import java.util.List;

public interface BlogDao {
    /**
     * 根据店铺ID查询其动态
     * @param storeId
     * @return
     */
    List<Blog> selectByStoreId(Integer storeId) throws Exception;

    /**
     * 删除数据
     * @param id
     */
    void delete(Integer id) throws Exception;

    /**
     * 添加数据
     * @param blog
     */
    void insertSelective(Blog blog) throws Exception;


}
