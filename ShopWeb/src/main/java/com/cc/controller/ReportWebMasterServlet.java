package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.Result;
import com.cc.po.Report;
import com.cc.service.Impl.ReportServiceImpl;
import com.cc.service.Impl.StoreServiceImpl;
import com.cc.service.ReportService;
import com.cc.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ReportWebMasterServlet extends BaseServlet{
    private final ReportService reportService = new ReportServiceImpl();
    private final StoreService storeService =new StoreServiceImpl();
    private static final Logger logger = LoggerFactory.getLogger(StoreWebMasterServlet.class);


    /**
     * 举报已处理（提醒）
     * @param request
     * @param response
     * @throws Exception
     */
    public void dealt(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        reportService.accept(id);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 举报已驳回（提醒）
     * @param request
     * @param response
     * @throws Exception
     */
    public void refuseRegister(HttpServletRequest request,HttpServletResponse response)throws Exception{
        int id = Integer.parseInt(request.getParameter("id"));
        reportService.refuse(id);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 举报信息列表
     * @param request
     * @param response
     * @throws Exception
     */
    public void selectAllReport(HttpServletRequest request,HttpServletResponse response)throws Exception{
        //调用service查询
        List<Report> reports = reportService.getAll();

        logger.debug("reports" + reports.toString());

        Result result = Result.success(reports);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

}
