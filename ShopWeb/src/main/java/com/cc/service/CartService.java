package com.cc.service;

import com.cc.po.Cart;
import com.cc.vo.CartVO;

import java.util.List;

public interface CartService {
    /**
     * 查询所有byIds
     * @return
     */
    List<CartVO> list(int[] ids) throws Exception;

    /**
     * 查询所有byUserId
     * @return
     * @throws Exception
     */
    List<CartVO> list(Integer userId) throws Exception;


    /**
     * 新增购物车项
     * @param productId
     * @param count
     * @return
     * @throws Exception
     */
    void add(Integer productId, Integer count,Integer userId)  throws Exception;

    /**
     * 判断购物车项是否有效
     * @param count
     */
    void validProduct(Integer productId,Integer count,Integer userId) throws Exception;

    /**
     * 修改商品信息
     * @param count
     * @param productId
     * @throws Exception
     */
    void update(Integer count,Integer productId,Integer userId) throws Exception;

    /**
     * 删除购物车项
     * @param productId
     * @throws Exception
     */
    void delete(Integer productId,Integer userId) throws Exception;

    /**
     * 批量删除
     * @param ids
     */
    void deleteInBatches(int[] ids) throws Exception;

    /**
     * 查询购物车项的数量
     * @throws Exception
     */
    Integer selectCount(Integer userId)throws Exception;
}
