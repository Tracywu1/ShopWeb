package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.Result;
import com.cc.filter.LoginCheckFilter;
import com.cc.service.CartService;
import com.cc.service.Impl.CartServiceImpl;
import com.cc.service.Impl.OrderServiceImpl;
import com.cc.service.OrderService;
import com.cc.vo.CartVO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/cart/*")
public class CartServlet extends BaseServlet{
    private final CartService cartService = new CartServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    private static final Logger logger = LoggerFactory.getLogger(CartServlet.class);

    /**
     * 购物车列表
     * @param request
     * @param response
     * @throws Exception
     */
    public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //调用service查询
        List<CartVO> cartVOS = cartService.list();

        Result result = Result.success(cartVOS);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 修改
     * @param request
     * @param response
     * @throws Exception
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //接收购买数量和商品的id
        int count = Integer.parseInt(request.getParameter("count"));
        int productId = Integer.parseInt(request.getParameter("productId"));

        //调用service更新数据
        cartService.update(count,productId);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 删除购物车项
     * @param request
     * @param response
     * @throws Exception
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //不能传入userId和cartId否则可以删除别人的购物车
        int productId = Integer.parseInt(request.getParameter("productId"));

        //调用service删除
        cartService.delete(productId);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 批量删除
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void deleteInBatches(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String params = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        logger.debug(params);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(params);
        int[] ids = mapper.treeToValue(node, int[].class);

        logger.debug(Arrays.toString(ids));

        //调用service批量删除
        cartService.deleteInBatches(ids);

        //响应成功标识
        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 加入购物车
     * @param request
     * @param response
     * @throws Exception
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int count = Integer.parseInt(request.getParameter("count"));

        //调用service添加
        cartService.add(productId,count);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 创建订单
     * @param request
     * @param response
     * @throws Exception
     */
    public void createOrder(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String params = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        logger.debug(params);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(params);
        int[] ids = mapper.treeToValue(node, int[].class);

        logger.debug(Arrays.toString(ids));

        String orderNo = orderService.createForCart(ids);
        orderService.pay(orderNo);

        Result result = Result.success(orderNo);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    public void count(HttpServletRequest request,HttpServletResponse response)throws Exception{
        int count = cartService.selectCount();

        Result result = Result.success(count);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

}
