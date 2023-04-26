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
     * 更新选择状态
     * @param userId
     * @param productId
     * @param selected
     */
    void updateSelect(Integer userId,Integer productId,Integer selected) throws Exception;
}
