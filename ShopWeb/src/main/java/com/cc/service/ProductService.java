package com.cc.service;

import com.cc.po.PageBean;
import com.cc.po.Product;
import com.cc.po.ProductApplication;

import java.util.List;

/**
 * @author 32119
 */
public interface ProductService {
    /**
     * 查询所有商品
     *
     * @return
     */
    List<Product> getAllProduct() throws Exception;

    /**
     * 新增商品
     *
     * @param product
     */
    void add(Product product) throws Exception;

    /**
     * 下架商品（店铺管理员）
     * @param id
     * @throws Exception
     */
    void delete(Integer id) throws Exception;

    /**
     * 批量下架
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

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    Product selectById(Integer id) throws Exception;
}
