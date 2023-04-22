package com.cc.dao;

import com.cc.po.Product;

import java.util.List;

public interface ProductDao {
    /**
     * 添加数据
     * @param product
     */
    void insert(Product product);

    /**
     * 根据id删除数据
     * @param id
     */
    void delete(Integer id);

    /**
     * 批量删除（ids）
     * @param ids
     */
    void deleteByIds(int[] ids);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    Product select(Integer id);

    /**
     * 根据商品名称查询商品（模糊查询）
     * @param productName
     * @return
     */
    List<Product> selectByProductName(String productName);

    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */
    List<Product> selectByPage(int begin, int size);

    /**
     * 查询总记录数
     * @return
     */
    int selectTotalCount();

    /**
     * 分页条件查询
     * @param begin
     * @param size
     * @return
     */
    List<Product> selectByPageAndCondition(int begin, int size, Product product);

    /**
     * 根据条件查询总记录数
     * @return
     */
    int selectTotalCountByCondition(Product product);

    /**
     * 修改数据
     * @param product
     */
    void update(Product product);
}
