package com.cc.dao;

import com.cc.po.Order;
import com.cc.po.Product;

import java.util.List;

public interface OrderDao {
    /**
     * 删除数据
     * @param id
     * @return
     */
    void delete(Integer id) throws Exception;


    /**
     * 有选择性地添加数据
     * @param order
     * @return
     */
    void insertSelective(Order order) throws Exception;

    /**
     * 查询所有
     * @return
     */
    List<Order> selectAll() throws Exception;

    /**
     * 根据id查询订单
     * @param id
     * @return
     */
    Order select(Integer id) throws Exception;

    /**
     * 根据订单编号查询订单
     * @param orderNo
     * @return
     */
    Order selectByOrderNo(String orderNo) throws Exception;

    /**
     * 有选择性地修改数据
     * @param order
     * @return
     */
    void updateByIdSelective(Order order) throws Exception;

    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */
    List<Order> selectByPage(int begin, int size) throws Exception;

    /**
     * 查询总记录数
     * @return
     */
    int selectTotalCount() throws Exception;

    /**
     * 分页条件查询
     * @param begin
     * @param size
     * @param orderNo
     * @return
     */
    List<Order> selectByPageAndCondition(int begin, int size, String orderNo) throws Exception;

    /**
     * 根据条件查询总记录数
     * @param orderNo
     * @return
     */
    int selectTotalCountByCondition(String orderNo) throws Exception;
}
