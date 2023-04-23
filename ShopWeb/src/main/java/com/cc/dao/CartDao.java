package com.cc.dao;

import com.cc.po.Cart;

import java.util.List;

public interface CartDao {
    /**
     * 删除数据
     * @param id
     * @return
     */
    void delete(Integer id) throws Exception;

    /**
     * 添加数据
     * @param cart
     * @throws Exception
     */
    void insert(Cart cart)throws Exception;

    /**
     * 查询所有
     * @return
     */
    List<Cart> selectAll() throws Exception;


    /**
     * 修改购买数量
     * @param count
     * @return
     */
    void update(Integer count,Integer id) throws Exception;

    /**
     * 批量删除（ids）
     * @param ids
     */
    void deleteByIds(int[] ids) throws Exception;
    
}
