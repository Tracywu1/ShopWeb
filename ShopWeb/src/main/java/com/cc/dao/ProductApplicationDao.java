package com.cc.dao;

import com.cc.po.Product;
import com.cc.po.ProductApplication;

import java.util.List;

public interface ProductApplicationDao {
    /**
     * 选择性地添加申请
     * @param productApplication
     * @throws Exception
     */
    void insertApplicationSelective(ProductApplication productApplication)throws Exception;

    /**
     * 查询所有商品申请信息
     * @return
     * @throws Exception
     */
    List<ProductApplication> selectAllProductApplication()throws  Exception;

    /**
     * 根据id查询商品申请信息
     * @param id
     * @return
     * @throws Exception
     */
    ProductApplication selectApplicationById(Integer id)throws Exception;

    /**
     * 选择性地修改商品申请状态
     * @param productApplication
     * @throws Exception
     */
    void updateStatusById(ProductApplication productApplication) throws Exception;

}
