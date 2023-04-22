package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.po.Product;
import com.cc.service.Impl.ProductServiceImpl;
import com.cc.service.ProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 32119
 */
@WebServlet("/product/*")
public class ProductServlet extends BaseServlet{
private ProductService productService = new ProductServiceImpl();

public void selectAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //调用service查询
    List<Product> products = productService.getAll();

    //转为json,写数据
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().write(JSON.toJSONString(products));
}

public void add(HttpServletRequest request, HttpServletResponse response){

}

}
