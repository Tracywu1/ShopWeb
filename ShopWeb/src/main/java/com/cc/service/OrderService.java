package com.cc.service;

import com.cc.po.PageBean;
import com.cc.po.Order;

import java.util.List;

public interface OrderService {
    /**
     * 查询所有
     * @return
     */
    List<Order> getAll() throws Exception;

    /**
     * 创建订单
     */
    String create() throws Exception;

    /**
     * 删除订单
     * @param id
     * @throws Exception
     */
    void delete(int id) throws Exception;

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
    PageBean<Order> selectByPage(int currentPage, int pageSize) throws Exception;

    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param orderNo
     * @return
     */
    PageBean<Order>  selectByPageAndCondition(int currentPage,int pageSize,String orderNo) throws Exception;

    /**
     * 发货
     * @param orderNo
     */
    void deliver(String orderNo) throws Exception;

    /**
     * 支付
     * @param orderNo
     * @throws Exception
     */
    void pay(String orderNo)throws Exception;

    /**
     * 订单完成
     * @param orderNo
     */
    void finish(String orderNo) throws Exception;
}
