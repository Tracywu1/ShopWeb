package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.Result;
import com.cc.po.Store;
import com.cc.service.CartService;
import com.cc.service.Impl.CartServiceImpl;
import com.cc.service.Impl.StoreServiceImpl;
import com.cc.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StoreServlet extends BaseServlet{

    private final StoreService storeService = new StoreServiceImpl();
    private static final Logger logger = LoggerFactory.getLogger(StoreServlet.class);

    /**
     * 展示店铺（简介及相关信息、商品信息）
     * @param request
     * @param response
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //接收店铺的id
        int id = Integer.parseInt(request.getParameter("id"));

        Store store = storeService.selectById(id);

        Result result = Result.success(store);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }


}
