package com.cc.dao;

import com.cc.po.OrderItem;

import java.util.List;

public interface OrderItemDao {
    /**
     * 选择性地添加数据
     * @param orderItem
     * @throws Exception
     */
    void insertSelective(OrderItem orderItem) throws Exception;

    /**
     * 根据id删除数据
     * @param id
     */
    void delete(Integer id) throws Exception;

    /**
     * 根据订单编号查询数据
     * @param orderNo
     * @return
     */
    List<OrderItem> selectByOrderNo(String orderNo) throws Exception;

    /**
     * 选择性地修改数据
     * @param orderItem
     * @throws Exception
     */
    void updateByIdSelective(OrderItem orderItem) throws Exception;
}
