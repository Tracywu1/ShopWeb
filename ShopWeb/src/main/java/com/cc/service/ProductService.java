package com.cc.service;

import com.cc.po.PageBean;
import com.cc.po.Product;

import java.util.List;

/**
 * @author 32119
 */
public interface ProductService {
    /**
     * 查询所有
     *
     * @return
     */
    List<Product> getAll() throws Exception;

    /**
     * 新增商品
     *
     * @param product
     */
    void add(Product product) throws Exception;

    void delete(int id) throws Exception;

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteInBatches(int[] ids) throws Exception;

    /**
     * 修改商品信息
     *
     * @param updateproduct
     */
    void update(Product updateproduct) throws Exception;

    /**
     * 分页查询
     *
     * @param currentPage 当前页码
     * @param pageSize    每页展示条数
     * @return
     */
    PageBean<Product> selectByPage(int currentPage, int pageSize) throws Exception;

    /**
     * 分页条件查询
     *
     * @param currentPage
     * @param pageSize
     * @param productName
     * @param storeName
     * @return
     */
    PageBean<Product> selectByPageAndCondition(int currentPage, int pageSize, String productName, String storeName) throws Exception;
}
