package com.cc.service;

import com.cc.po.Cart;
import com.cc.vo.CartVO;

import java.util.List;

public interface CartService {
    /**
     * 查询所有
     * @return
     */
    List<CartVO> list() throws Exception;

    /**
     * 新增购物车项
     * @param productId
     * @param count
     * @return
     * @throws Exception
     */
    void add(Integer productId, Integer count)  throws Exception;

    /**
     * 判断购物车项是否有效
     * @param count
     */
    void validProduct(Integer productId,Integer count) throws Exception;

    /**
     * 修改商品信息
     * @param count
     * @param productId
     * @throws Exception
     */
    void update(Integer count,Integer productId) throws Exception;

    /**
     * 删除购物车项
     * @param productId
     * @throws Exception
     */
    void delete(Integer productId) throws Exception;

    /**
     * 是否选中
     * @param productId
     * @param selected
     */
    void selectOrNot(Integer productId, Integer selected) throws Exception;

    /**
     * 是否全部选中
     * @param selected
     */
    void selectAllOrNot(Integer selected) throws Exception;

    /**
     * 查询购物车项的数量
     * @throws Exception
     */
    Integer selectCount()throws Exception;
}
