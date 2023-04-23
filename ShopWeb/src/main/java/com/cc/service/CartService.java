package com.cc.service;

import com.cc.po.Cart;

import java.util.List;

public interface CartService {
    /**
     * 查询所有
     * @return
     */
    List<Cart> getAll() throws Exception;

    /**
     * 新增购物车项
     * @param cart
     */
    void add(Cart cart) throws Exception;

    /**
     * 删除订单
     * @param id
     * @throws Exception
     */
    void delete(int id) throws Exception;

    /**
     * 修改商品信息
     * @param count
     * @param id
     */
    void update(int count,int id) throws Exception;

    /**
     * 批量删除
     * @param ids
     */
    void deleteInBatches(int[]ids) throws Exception;
}
