package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.po.PageBean;
import com.cc.po.Product;
import com.cc.exception.Result;
import com.cc.service.Impl.ProductServiceImpl;
import com.cc.service.ProductService;

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
@WebServlet("/product/*")
public class ProductServlet extends BaseServlet {
    private final ProductService productService = new ProductServiceImpl();

    /**
     * 查询所有
     * @param request
     * @param response
     * @throws Exception
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //调用service查询
        List<Product> products = productService.getAll();

        Result result = Result.success(products);
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

        //转为Product对象
        Product product = JSON.parseObject(params, Product.class);

        //调用service添加
        productService.add(product);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 删除商品
     * @param request
     * @param response
     * @throws Exception
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
        int id = Integer.parseInt(request.getParameter("id"));

        //调用service添加
        productService.delete(id);

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
        productService.deleteInBatches(ids);

        //响应成功标识
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
        //1.接收数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为Product对象
        Product product = JSON.parseObject(params, Product.class);

        //调用service添加
        productService.update(product);

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

        // 调用service查询
        PageBean<Product> pageBean = productService.selectByPage(currentPage, pageSize);

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

        // 获取查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        // 转为 Product
        Product product = JSON.parseObject(params, Product.class);

        // 调用service查询
        PageBean<Product> pageBean = productService.selectByPageAndCondition(currentPage, pageSize, product);

        //响应成功标识
        Result result = Result.success(pageBean);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
