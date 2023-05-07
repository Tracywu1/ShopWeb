package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.Result;
import com.cc.service.Impl.OrderServiceImpl;
import com.cc.service.OrderService;
import com.cc.vo.OrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 32119
 */
@WebServlet("/order/*")
public class OrderServlet extends BaseServlet {
    private static final Logger logger = LoggerFactory.getLogger(OrderServlet.class);
    private final OrderService orderService = new OrderServiceImpl();

    /**
     * 创建订单(立即购买)
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int count = Integer.parseInt(request.getParameter("count"));
        int addressId = Integer.parseInt(request.getParameter("addressId"));
        Integer userId = Integer.valueOf(request.getParameter("userId"));

        //调用service添加
        String orderNo = orderService.create(productId,count,addressId,userId);
        orderService.pay(orderNo);

        Result result = Result.success(orderNo);
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
        Integer userId = Integer.valueOf(request.getParameter("userId"));

        OrderVO orderVO = orderService.detail(orderNo,userId);

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
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        //调用service查询
        List<OrderVO> orderVOList = orderService.listNotShippedForCustomer(userId);

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
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        //调用service查询
        List<OrderVO> orderVOList = orderService.listDeliveredForCustomer(userId);

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
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        //调用service查询
        List<OrderVO> orderVOList = orderService.listReceivedForCustomer(userId);

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
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        //调用service查询
        List<OrderVO> orderVOList = orderService.listAfterSalesServiceForCustomer(userId);

        logger.debug("orderVOList:" + orderVOList.toString());

        Result result = Result.success(orderVOList);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

}
