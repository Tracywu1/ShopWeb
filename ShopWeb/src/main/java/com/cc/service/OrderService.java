package com.cc.service;

import com.cc.po.PageBean;
import com.cc.po.Order;
import com.cc.vo.OrderVO;

import java.util.List;

public interface OrderService {
    /**
     * 店铺管理员的订单列表
     * @return
     */
    List<OrderVO> listForManager() throws Exception;

    /**
     * 用户的未发货订单列表
     * @return
     */
    List<OrderVO> listNotShippedForCustomer() throws Exception;

    /**
     * 用户的已发货订单列表
     * @return
     */
    List<OrderVO> listDeliveredForCustomer() throws Exception;

    /**
     * 用户的已收货订单列表
     * @return
     */
    List<OrderVO> listReceivedForCustomer() throws Exception;

    /**
     * 用户的售后订单列表
     * @return
     */
    List<OrderVO> listAfterSalesService() throws Exception;

    /**
     * 订单详情
     * @param orderNo
     * @return
     */
    OrderVO detail(String orderNo) throws Exception;

    /**
     * 创建订单
     */
    String create() throws Exception;

    /**
     * 修改商品信息
     * @param updateorder
     */
    void update(Order updateorder) throws Exception;

    /**
     * 分页查询
     * @param currentPage  当前页码
     * @param pageSize   每页展示条数
     * @return
     */
    PageBean<OrderVO> selectByPage(int currentPage, int pageSize) throws Exception;

    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param orderNo
     * @return
     */
    PageBean<OrderVO>  selectByPageAndCondition(int currentPage,int pageSize,String orderNo) throws Exception;

    /**
     * 发货
     * @param orderNo
     */
    void deliver(String orderNo) throws Exception;

    /**
     * 收货
     * @param orderNo
     */
    void receive(String orderNo) throws Exception;

    /**
     * 支付
     * @param orderNo
     * @throws Exception
     */
    void pay(String orderNo)throws Exception;
}
