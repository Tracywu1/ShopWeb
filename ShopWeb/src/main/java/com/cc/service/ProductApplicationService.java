package com.cc.service;

import com.cc.po.ProductApplication;

import java.util.List;

public interface ProductApplicationService {
    /**
     * 新增上架申请
     * @param productApplication
     * @throws Exception
     */
    void addApplication(ProductApplication productApplication)throws Exception;

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
    ProductApplication getById(Integer id)throws Exception;

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<ProductApplication> getAll()throws Exception;
}
