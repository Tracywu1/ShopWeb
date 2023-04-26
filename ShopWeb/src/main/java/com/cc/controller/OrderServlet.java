package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.Result;
import com.cc.po.PageBean;
import com.cc.po.Order;
import com.cc.service.Impl.OrderServiceImpl;
import com.cc.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * @author 32119
 */
@WebServlet("/order/*")
public class OrderServlet extends BaseServlet {
    private final OrderService orderService = new OrderServiceImpl();

    private static final Logger logger = LoggerFactory.getLogger(OrderServlet.class);

    /**
     * 查询所有
     * @param request
     * @param response
     * @throws Exception
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //调用service查询
        List<Order> orders = orderService.getAll();

        logger.debug("orders"+orders.toString());

        Result result = Result.success(orders);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 添加数据
     * @param request
     * @param response
     * @throws Exception
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.接收数据
        BufferedReader br = request.getReader();
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        String params = sb.toString(); // 获取完整的请求体字符

        logger.debug("params"+params);

        //转为Order对象
        Order order = JSON.parseObject(params, Order.class);

        //调用service添加
        orderService.create(order);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 删除订单
     * @param request
     * @param response
     * @throws Exception
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
        int id = Integer.parseInt(request.getParameter("id"));

        logger.debug(String.valueOf(id));

        //调用service添加
        orderService.delete(id);

        Result result = Result.success();
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
        BufferedReader br = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        String params = sb.toString();

        logger.debug("params:"+params);

        //转为Order对象
        Order order = JSON.parseObject(params, Order.class);

        //调用service添加
        orderService.update(order);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));

    }

    /**
     * 分页查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        logger.debug("currentPage"+currentPage);
        logger.debug("pageSize"+pageSize);

        // 调用service查询
        PageBean<Order> pageBean = orderService.selectByPage(currentPage, pageSize);

        //响应成功标识
        Result result = Result.success(pageBean);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 分页条件查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        logger.debug("currentPage:"+currentPage);
        logger.debug("pageSize:"+pageSize);

        String OrderNo = request.getParameter("OderNo");

        logger.debug("storeName:"+OrderNo);

        // 调用service查询
        PageBean<Order> pageBean = orderService.selectByPageAndCondition(currentPage, pageSize,OrderNo);

        //响应成功标识
        Result result = Result.success(pageBean);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
