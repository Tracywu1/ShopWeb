package com.cc.service;

import com.cc.po.Cart;
import com.cc.vo.CartVO;

import java.util.List;

public interface CartService {
    /**
     * 查询所有
     * @return
     */
    List<CartVO> list(Integer userId) throws Exception;

    /**
     * 新增购物车项
     * @param userId
     * @param productId
     * @param count
     * @return
     * @throws Exception
     */
    void add(Integer userId, Integer productId, Integer count)  throws Exception;

    /**
     * 判断购物车项是否有效
     * @param userId
     * @param count
     */
    void validProduct(Integer userId, Integer count) throws Exception;

    /**
     * 修改商品信息
     * @param count
     * @param userId
     * @param productId
     * @throws Exception
     */
    void update(Integer count,Integer userId,Integer productId) throws Exception;

    /**
     * 删除购物车项
     * @param userId
     * @param productId
     * @throws Exception
     */
    void delete(Integer userId,Integer productId) throws Exception;

    /**
     * 是否选中
     * @param userId
     * @param productId
     * @param selected
     */
    void selectOrNot(Integer userId, Integer productId, Integer selected) throws Exception;

    /**
     * 是否全部选中
     * @param userId
     * @param selected
     */
    void selectAllOrNot(Integer userId, Integer selected) throws Exception;
}
