package com.cc.dao;

import com.cc.po.Order;
import com.cc.po.Product;
import com.cc.vo.OrderVO;

import java.util.List;

public interface OrderDao {
    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    void delete(Integer id) throws Exception;


    /**
     * 有选择性地添加数据
     *
     * @param order
     * @return
     */
    void insertSelective(Order order) throws Exception;

    /**
     * 为店铺店员查询所有订单
     *
     * @return
     */
    List<Order> selectAllForManager(Integer storeId) throws Exception;

    /**
     * 为用户查询未发货订单
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<Order> selectNotShippedForCustomer(Integer userId) throws Exception;

    /**
     * 为用户查询已发货订单
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<Order> selectDeliveredForCustomer(Integer userId) throws Exception;

    /**
     * 为用户查询已收货订单
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<Order> selectReceivedForCustomer(Integer userId) throws Exception;

    /**
     * 为用户查询售后订单
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<Order> selectAfterSalesService(Integer userId) throws Exception;

    /**
     * 根据订单编号查询订单
     *
     * @param orderNo
     * @return
     */
    Order selectByOrderNo(String orderNo) throws Exception;

    /**
     * 有选择性地修改数据
     *
     * @param order
     * @return
     */
    void updateByIdSelective(Order order) throws Exception;

    /**
     * 分页查询
     *
     * @param begin
     * @param size
     * @return
     */
    List<OrderVO> selectByPage(Integer begin, Integer size, Integer storeId) throws Exception;

    /**
     * 查询总记录数
     *
     * @return
     */
    int selectTotalCount() throws Exception;

    /**
     * 分页条件查询
     *
     * @param begin
     * @param size
     * @param orderNo
     * @param storeId
     * @return
     */
    List<OrderVO> selectByPageAndCondition(int begin, int size, Integer storeId, String orderNo) throws Exception;

    /**
     * 根据条件查询总记录数
     *
     * @param orderNo
     * @return
     */
    int selectTotalCountByCondition(String orderNo) throws Exception;
}
