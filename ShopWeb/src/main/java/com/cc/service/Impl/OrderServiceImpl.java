package com.cc.service.Impl;

import com.cc.dao.Impl.OrderDaoImpl;
import com.cc.dao.OrderDao;
import com.cc.exception.MyRunTimeException;
import com.cc.exception.ResultCode;
import com.cc.po.PageBean;
import com.cc.po.Order;
import com.cc.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    @Override
    public List<Order> getAll() throws Exception {
        return orderDao.selectAll();
    }

    @Override
    public void add(Order order) throws Exception {
        orderDao.insertSelective(order);
    }

    @Override
    public void delete(int id) throws Exception {
        Order orderOld = orderDao.select(id);

        //查询不到该数据，无法删除
        if(orderOld == null){
            throw new MyRunTimeException(ResultCode.DELETE_FAILED);
        }

        orderDao.delete(id);
    }

    @Override
    public void update(Order updateOrder) throws Exception {
        orderDao.updateByIdSelective(updateOrder);
    }

    @Override
    public PageBean<Order> selectByPage(int currentPage, int pageSize) throws Exception {
        // 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        int size = pageSize;

        // 查询当前页数据
        List<Order> rows = orderDao.selectByPage(begin, size);

        //查询总记录数
        int totalCount = orderDao.selectTotalCount();

        //封装PageBean对象
        PageBean<Order> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        return pageBean;
    }

    @Override
    public PageBean<Order> selectByPageAndCondition(int currentPage, int pageSize, String orderNo) throws Exception {
        // 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        int size = pageSize;

        // 查询当前页数据
        List<Order> rows = orderDao.selectByPageAndCondition(begin, size, orderNo);

        // 查询总记录数
        int totalCount = orderDao.selectTotalCountByCondition(orderNo);

        // 封装PageBean对象
        PageBean<Order> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        return pageBean;
    }

}
