package com.cc.service;

import com.cc.po.StoreApplication;

import java.util.List;

public interface StoreApplicationService {
    /**
     * 新增上架申请
     * @param storeApplication
     * @throws Exception
     */
    void addApplication(StoreApplication storeApplication)throws Exception;

    /**
     * 同意申请
     * @param id
     * @throws Exception
     */
    void accept(Integer id)throws Exception;

    /**
     * 拒绝申请
     * @param id
     * @throws Exception
     */
    void refuse(Integer id)throws Exception;

    /**
     * 根据id查询
     * @param id
     * @throws Exception
     */
    StoreApplication getById(Integer id)throws Exception;

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<StoreApplication> getAll()throws Exception;
}
