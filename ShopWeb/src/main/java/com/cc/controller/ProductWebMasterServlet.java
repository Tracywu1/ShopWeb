package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.Result;
import com.cc.po.PageBean;
import com.cc.po.Product;
import com.cc.po.ProductApplication;
import com.cc.service.Impl.ProductApplicationServiceImpl;
import com.cc.service.Impl.ProductServiceImpl;
import com.cc.service.ProductApplicationService;
import com.cc.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/product/master/*")
public class ProductWebMasterServlet extends BaseServlet {
    private final ProductApplicationService productApplicationService = new ProductApplicationServiceImpl();
    private final ProductService productService =new ProductServiceImpl();
    private static final Logger logger = LoggerFactory.getLogger(ProductWebMasterServlet.class);
    /**
     * 同意上架（提醒）
     * @param request
     * @param response
     * @throws Exception
     */
    public void acceptShelf(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        productApplicationService.accept(id);
        ProductApplication productApplication = productApplicationService.getById(id);

        Product product =new Product();
        product.setStoreId(productApplication.getStoreId());
        product.setStoreName(productApplication.getStoreName());
        product.setProductName(productApplication.getProductName());
        product.setDescription(productApplication.getDescription());
        product.setImage(productApplication.getImage());
        product.setPrice(productApplication.getPrice());
        product.setProductCount(productApplication.getProductCount());

        productService.add(product);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 拒绝上架（提醒）
     * @param request
     * @param response
     * @throws Exception
     */
    public void refuseShelf(HttpServletRequest request,HttpServletResponse response)throws Exception{
        int id = Integer.parseInt(request.getParameter("id"));
        productApplicationService.refuse(id);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 商品申请列表
     * @param request
     * @param response
     * @throws Exception
     */
    public void selectAllProductApplication(HttpServletRequest request,HttpServletResponse response)throws Exception{
        //调用service查询
        List<ProductApplication> productApplications = productApplicationService.getAll();

        logger.debug("productApplications" + productApplications.toString());

        Result result = Result.success(productApplications);
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


}
