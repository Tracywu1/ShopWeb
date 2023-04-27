package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.Result;
import com.cc.po.Product;
import com.cc.po.ProductApplication;
import com.cc.po.Store;
import com.cc.po.StoreApplication;
import com.cc.service.Impl.ProductApplicationServiceImpl;
import com.cc.service.Impl.ProductServiceImpl;
import com.cc.service.Impl.StoreApplicationServiceImpl;
import com.cc.service.Impl.StoreServiceImpl;
import com.cc.service.ProductApplicationService;
import com.cc.service.ProductService;
import com.cc.service.StoreApplicationService;
import com.cc.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/store/webMaster/*")
public class StoreWebMasterServlet {
    private final StoreApplicationService storeApplicationService = new StoreApplicationServiceImpl();
    private final StoreService storeService =new StoreServiceImpl();
    private static final Logger logger = LoggerFactory.getLogger(StoreWebMasterServlet.class);

    /**
     * 同意注册（提醒）
     * @param request
     * @param response
     * @throws Exception
     */
    public void acceptRegister(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        storeApplicationService.accept(id);
        StoreApplication storeApplication = storeApplicationService.getById(id);

        Store store =new Store();
        store.setManagerId(storeApplication.getUserId());
        store.setStoreName(storeApplication.getStoreName());
        store.setStoreName(storeApplication.getStoreName());
        store.setDescription(storeApplication.getDescription());
        store.setLogo(storeApplication.getLogo());

        storeService.add(store);

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
    public void refuseRegister(HttpServletRequest request,HttpServletResponse response)throws Exception{
        int id = Integer.parseInt(request.getParameter("id"));
        storeApplicationService.refuse(id);

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
    public void selectAll(HttpServletRequest request,HttpServletResponse response)throws Exception{
        //调用service查询
        List<StoreApplication> storeApplications = storeApplicationService.getAll();

        logger.debug("storeApplications" + storeApplications.toString());

        Result result = Result.success(storeApplications);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
