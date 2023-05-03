package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.Result;
import com.cc.filter.LoginCheckFilter;
import com.cc.service.CartService;
import com.cc.service.Impl.CartServiceImpl;
import com.cc.service.Impl.OrderServiceImpl;
import com.cc.service.OrderService;
import com.cc.vo.CartVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/cart/*")
public class CartServlet extends BaseServlet{
    private final CartService cartService = new CartServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    /**
     * 购物车列表
     * @param request
     * @param response
     * @throws Exception
     */
    public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //调用service查询
        List<CartVO> cartVOS = cartService.list(LoginCheckFilter.currentUser.getId());

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
        cartService.update(count,LoginCheckFilter.currentUser.getId(),productId);

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
        cartService.delete(LoginCheckFilter.currentUser.getId(),productId);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 选择/不选择购物车中某商品
     * @param request
     * @param response
     * @throws Exception
     */
    public void select(HttpServletRequest request, HttpServletResponse response) throws Exception{
        int productId = Integer.parseInt(request.getParameter("productId"));
        int selected = Integer.parseInt(request.getParameter("selected"));

        //调用service
        cartService.selectOrNot(LoginCheckFilter.currentUser.getId(),productId,selected);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 全选择/全部选择购物车中的商品
     * @param request
     * @param response
     * @throws Exception
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
        int selected = Integer.parseInt(request.getParameter("selected"));

        //调用service
        cartService.selectAllOrNot(LoginCheckFilter.currentUser.getId(),selected);

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
        cartService.add(1,productId,count);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    public void createOrder(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String orderNo = orderService.createForCart();

        Result result = Result.success(orderNo);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

}
