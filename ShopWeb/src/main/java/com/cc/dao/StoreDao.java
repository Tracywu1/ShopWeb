package com.cc.dao;

import com.cc.po.Product;
import com.cc.po.Store;

import java.util.List;

public interface StoreDao {
    /**
     * 选择性地添加数据
     * @param store
     * @throws Exception
     */
    void insertSelective(Store store) throws Exception;

    /**
     * 查询所有商品
     * @return
     */
    List<Store> selectAllStore() throws Exception;

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    Store selectStoreById(Integer id) throws Exception;

    /**
     * 选择性地修改商品数据
     * @param store
     * @throws Exception
     */
    void updateByIdSelective(Store store) throws Exception;
}
