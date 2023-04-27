package com.cc.dao;

import com.cc.po.StoreApplication;

import java.util.List;

public interface StoreApplicationDao {
    /**
     * 选择性地添加申请
     * @param storeApplication
     * @throws Exception
     */
    void insertApplicationSelective(StoreApplication storeApplication)throws Exception;

    /**
     * 查询所有商品申请信息
     * @return
     * @throws Exception
     */
    List<StoreApplication> selectAllStoreApplication()throws  Exception;

    /**
     * 根据id查询商品申请信息
     * @param id
     * @return
     * @throws Exception
     */
    StoreApplication selectApplicationById(Integer id)throws Exception;

    /**
     * 选择性地修改商品申请数据
     * @param storeApplication
     * @throws Exception
     */
    void updateByIdSelective(StoreApplication storeApplication) throws Exception;

}
