package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.Result;
import com.cc.po.Product;
import com.cc.po.ProductApplication;
import com.cc.service.Impl.ProductApplicationServiceImpl;
import com.cc.service.Impl.ProductServiceImpl;
import com.cc.service.ProductApplicationService;
import com.cc.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

}
