package com.cc.dao;

import com.cc.po.Product;

import java.util.List;

public interface ProductDao {

    /**
     * 选择性地添加数据
     * @param product
     * @throws Exception
     */

    void insertSelective(Product product) throws Exception;

    /**
     * 根据id删除数据
     * @param id
     */
    void delete(Integer id) throws Exception;

    /**
     * 批量删除（ids）
     * @param ids
     */
    void deleteByIds(int[] ids) throws Exception;

    /**
     * 查询所有
     * @return
     */
    List<Product> selectAll() throws Exception;

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    Product select(Integer id) throws Exception;

    /**
     * 根据商品名称查询商品（精确查询）
     * @param productName
     * @return
     * @throws Exception
     */
    Product selectByProductName(String productName)throws Exception;

    /**
     * 根据商品名称查询商品（模糊查询）
     * @param productName
     * @return
     */
    List<Product> selectByProductNameList(String productName) throws Exception;

    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */
    List<Product> selectByPage(int begin, int size) throws Exception;

    /**
     * 查询总记录数
     * @return
     */
    int selectTotalCount() throws Exception;

    /**
     * 分页条件查询
     * @param begin
     * @param size
     * @param productName
     * @param storeName
     * @return
     */
    List<Product> selectByPageAndCondition(int begin, int size, String productName,String storeName) throws Exception;

    /**
     * 根据条件查询总记录数
     * @param productName
     * @param storeName
     * @return
     */
    int selectTotalCountByCondition(String productName , String storeName) throws Exception;

    /**
     * 修改数据
     * @param product
     */
    void update(Product product) throws Exception;

    /**
     * 选择性地修改数据
     * @param product
     * @throws Exception
     */
    void updateByIdSelective(Product product) throws Exception;
}
