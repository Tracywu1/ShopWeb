package com.cc.service;

import com.cc.po.Store;

public interface StoreService {
    /**
     * 新增店铺
     * @param store
     */
    void add (Store store) throws Exception;


    /**
     * 根据id查询店铺信息
     * @param id
     * @return
     * @throws Exception
     */
    Store selectById(Integer id)throws Exception;
}
