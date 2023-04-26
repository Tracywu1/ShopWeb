package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.Result;
import com.cc.po.PageBean;
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
@WebServlet("/oder/manager/*")
public class OrderManagerServlet {
    private static final Logger logger = LoggerFactory.getLogger(OrderManagerServlet.class);
    private final OrderService orderService = new OrderServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<OrderVO> orderVOList = orderService.listForManager();

        Result result = Result.success(orderVOList);
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

        logger.debug("currentPage" + currentPage);
        logger.debug("pageSize" + pageSize);

        // 调用service查询
        PageBean<OrderVO> pageBean = orderService.selectByPage(currentPage, pageSize);

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

        logger.debug("currentPage:" + currentPage);
        logger.debug("pageSize:" + pageSize);

        String OrderNo = request.getParameter("OderNo");

        logger.debug("storeName:" + OrderNo);

        // 调用service查询
        PageBean<OrderVO> pageBean = orderService.selectByPageAndCondition(currentPage, pageSize, OrderNo);

        //响应成功标识
        Result result = Result.success(pageBean);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 发货
     * @param request
     * @param response
     * @throws Exception
     */
    public void delivered(HttpServletRequest request, HttpServletResponse response) throws Exception {
        orderService.deliver(request.getParameter("orderNo"));

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
