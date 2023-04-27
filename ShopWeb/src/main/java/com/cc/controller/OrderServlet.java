package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.Result;
import com.cc.po.PageBean;
import com.cc.po.Order;
import com.cc.service.Impl.OrderServiceImpl;
import com.cc.service.OrderService;
import com.cc.vo.OrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 32119
 */
@WebServlet("/order/*")
public class OrderServlet extends BaseServlet {
    private static final Logger logger = LoggerFactory.getLogger(OrderServlet.class);
    private final OrderService orderService = new OrderServiceImpl();

    /**
     * 创建订单
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //调用service添加//加个createOrderReq
        orderService.create();

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 查看订单详情
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String orderNo = request.getParameter("orderNo");

        OrderVO orderVO = orderService.detail(orderNo);

        Result result = Result.success(orderVO);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 未发货订单列表
     * @param request
     * @param response
     * @throws Exception
     */
    public void listNotShipped(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //调用service查询
        List<OrderVO> orderVOList = orderService.listNotShippedForCustomer();

        logger.debug("orderVOList:" + orderVOList.toString());

        Result result = Result.success(orderVOList);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 已发货订单列表
     * @param request
     * @param response
     * @throws Exception
     */
    public void listDelivered(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //调用service查询
        List<OrderVO> orderVOList = orderService.listDeliveredForCustomer();

        logger.debug("orderVOList:" + orderVOList.toString());

        Result result = Result.success(orderVOList);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 已收货订单列表
     * @param request
     * @param response
     * @throws Exception
     */
    public void listReceived(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //调用service查询
        List<OrderVO> orderVOList = orderService.listReceivedForCustomer();

        logger.debug("orderVOList:" + orderVOList.toString());

        Result result = Result.success(orderVOList);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 售后订单列表
     * @param request
     * @param response
     * @throws Exception
     */
    public void listAfterSalesService(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //调用service查询
        List<OrderVO> orderVOList = orderService.listAfterSalesServiceForCustomer();

        logger.debug("orderVOList:" + orderVOList.toString());

        Result result = Result.success(orderVOList);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 支付接口
     * @param request
     * @param response
     * @throws Exception
     */
    public void pay(HttpServletRequest request, HttpServletResponse response)throws Exception{
        orderService.pay(request.getParameter("orderNo"));

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

}
