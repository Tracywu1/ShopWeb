package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.po.PageBean;
import com.cc.po.Product;
import com.cc.exception.Result;
import com.cc.po.ProductApplication;
import com.cc.service.Impl.ProductApplicationServiceImpl;
import com.cc.service.Impl.ProductServiceImpl;
import com.cc.service.ProductApplicationService;
import com.cc.service.ProductService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author 32119
 */
@WebServlet("/product/manager/*")
public class ProductManagerServlet extends BaseServlet {
    private static final Logger logger = LoggerFactory.getLogger(ProductManagerServlet.class);

    private final ProductService productService = new ProductServiceImpl();
    private final ProductApplicationService productApplicationService = new ProductApplicationServiceImpl();


    /**
     * 申请上架
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String params = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        logger.debug("params:" + params);

        //转为ProductApplication对象
        ProductApplication productApplication = JSON.parseObject(params, ProductApplication.class);

        logger.debug("productApplication:"+productApplication);

        //调用service添加
        productApplicationService.addApplication(productApplication);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 下架商品
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));

        logger.debug(String.valueOf(id));

        //调用service添加
        productService.delete(id);

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
        productService.deleteInBatches(ids);

        //响应成功标识
        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 修改
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String params = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        logger.debug(params);

        //转为Product对象
        Product product = JSON.parseObject(params, Product.class);

        logger.debug(String.valueOf(product));

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

        logger.debug("currentPage" + currentPage);
        logger.debug("pageSize" + pageSize);

        // 调用service查询
        PageBean<Product> pageBean = productService.selectByPage(currentPage, pageSize);

        //响应成功标识
        Result result = Result.success(pageBean);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 查询所有
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //调用service查询
        List<Product> products = productService.getAllProduct();

        logger.debug("products" + products.toString());

        Result result = Result.success(products);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}

