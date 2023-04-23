package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.Result;
import com.cc.po.Cart;
import com.cc.service.CartService;
import com.cc.service.Impl.CartServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.List;

@WebServlet("/cart/*")
public class CartServlet extends BaseServlet{
    private CartService cartService = new CartServiceImpl();

    /**
     * 查询所有
     * @param request
     * @param response
     * @throws Exception
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //调用service查询
        List<Cart> carts = cartService.getAll();

        Result result = Result.success(carts);
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
        String params = br.readLine();//json字符串

        //转为Cart对象
        Cart cart = JSON.parseObject(params, Cart.class);

        //调用service添加
        cartService.add(cart);

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
        //接收购买数量和购物车项的id
        int count = Integer.parseInt(request.getParameter("count"));
        int id = Integer.parseInt(request.getParameter("id"));

        //调用service添加
        cartService.update(count,id);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 批量删除
     * @param request
     * @param response
     * @throws Exception
     */
    public void deleteInBatches(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.接收数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为int[]
        int[] ids = JSON.parseObject(params, int[].class);

        //调用service批量删除
        cartService.deleteInBatches(ids);

        //响应成功标识
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
        int id = Integer.parseInt(request.getParameter("id"));

        //调用service添加
        cartService.delete(id);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
