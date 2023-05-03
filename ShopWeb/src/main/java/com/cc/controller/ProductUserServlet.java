package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.Result;
import com.cc.filter.LoginCheckFilter;
import com.cc.po.PageBean;
import com.cc.po.Product;
import com.cc.service.CartService;
import com.cc.service.Impl.CartServiceImpl;
import com.cc.service.Impl.ProductServiceImpl;
import com.cc.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/product/user/*")
public class ProductUserServlet extends BaseServlet{

    private final CartService cartService = new CartServiceImpl();
    private final ProductService productService =new ProductServiceImpl();
    private static final Logger logger = LoggerFactory.getLogger(ProductUserServlet.class);

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

        logger.debug(_currentPage);
        logger.debug(_pageSize);

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        logger.debug("currentPage:" + currentPage);
        logger.debug("pageSize:" + pageSize);

        String productName = request.getParameter("productName");
        String storeName = request.getParameter("storeName");

        logger.debug("productName:" + productName);
        logger.debug("storeName:" + storeName);

        // 调用service查询
        PageBean<Product> pageBean = productService.selectByPageAndCondition(currentPage, pageSize, productName, storeName);

        //响应成功标识
        Result result = Result.success(pageBean);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    public void selectById(HttpServletRequest request, HttpServletResponse response) throws Exception{
        int id = Integer.parseInt(request.getParameter("id"));

        logger.debug("id:"+id);

        Product product = productService.selectById(id);

        //响应成功标识
        Result result = Result.success(product);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
