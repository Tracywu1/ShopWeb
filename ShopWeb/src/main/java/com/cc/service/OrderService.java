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
    List<OrderVO> listForManager(Integer userId) throws Exception;

    /**
     * 用户的未发货订单列表
     * @return
     */
    List<OrderVO> listNotShippedForCustomer(Integer userId) throws Exception;

    /**
     * 用户的已发货订单列表
     * @return
     */
    List<OrderVO> listDeliveredForCustomer(Integer userId) throws Exception;

    /**
     * 用户的已收货订单列表
     * @return
     */
    List<OrderVO> listReceivedForCustomer(Integer userId) throws Exception;

    /**
     * 用户的售后订单列表
     * @return
     */
    List<OrderVO> listAfterSalesServiceForCustomer(Integer userId) throws Exception;

    /**
     * 订单详情
     * @param orderNo
     * @return
     */
    OrderVO detail(String orderNo,Integer userId) throws Exception;

    /**
     * 为购物车中勾选的商品创建订单
     * @param ids
     * @return
     * @throws Exception
     */
    String createForCart(int[] ids,Integer userId) throws Exception;

    /**
     * 为立即购买的商品创建订单
     * @return
     * @throws Exception
     */
    String create(Integer productId,Integer count,Integer addressId,Integer userId)throws Exception;

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
    PageBean<OrderVO> selectByPage(int currentPage, int pageSize,Integer storeId) throws Exception;

    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param orderNo
     * @return
     */
    PageBean<OrderVO>  selectByPageAndCondition(int currentPage,int pageSize,String orderNo,Integer storeId) throws Exception;

    /**
     * 发货
     * @param orderNo
     */
    void deliver(String orderNo) throws Exception;

    /**
     * 收货
     * @param orderNo
     */
    void received(String orderNo) throws Exception;

    /**
     * 售后
     * @param orderNo
     * @throws Exception
     */
    void afterSaleService(String orderNo)throws Exception;

    /**
     * 支付
     * @param orderNo
     * @throws Exception
     */
    void pay(String orderNo)throws Exception;
}
