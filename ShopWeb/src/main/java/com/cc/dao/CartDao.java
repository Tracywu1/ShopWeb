package com.cc.dao;

import com.cc.po.Cart;
import com.cc.vo.CartVO;

import java.util.List;

public interface CartDao {
    /**
     * 删除数据
     * @param id
     * @return
     */
    void delete(Integer id) throws Exception;

    /**
     * 批量删除（ids）
     * @param ids
     */
    void deleteByIds(int[] ids) throws Exception;

    /**
     * 添加数据
     * @param cart
     * @throws Exception
     */
    void insert(Cart cart)throws Exception;

    /**
     * 选择性添加数据
     * @param cart
     */
    void insertSelective(Cart cart) throws Exception;

    /**
     * 查询所有
     * @return
     */
    List<CartVO> selectAll(Integer userId) throws Exception;

    /**
     * 根据ids查询所有
     * @param ids
     * @return
     * @throws Exception
     */
    public List<CartVO> selectByIds(int[] ids)throws Exception;

    /**
     * 根据ID查询
     * @param userId
     * @param productId
     * @return
     */
    Cart selectByUserIdAndProductId(Integer userId, Integer productId) throws Exception;

    /**
     * 修改购买数量
     * @param count
     * @return
     */
    void updateCount(Integer count,Integer id) throws Exception;

    /**
     * 查询购物车项的数量
     * @param userId
     * @return
     * @throws Exception
     */
    Integer selectCount(Integer userId)throws Exception;
}
