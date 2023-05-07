package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.Result;
import com.cc.po.Address;
import com.cc.service.AddressService;
import com.cc.service.Impl.AddressServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/address/*")
public class AddressServlet extends BaseServlet{
    private static final Logger logger = LoggerFactory.getLogger(AddressServlet.class);
    private final AddressService addressService = new AddressServiceImpl();

    public void add(HttpServletRequest request , HttpServletResponse response) throws Exception {
        String params = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        logger.debug(params);

        //转为Address对象(要有userId)
        Address address = JSON.parseObject(params, Address.class);

        logger.debug("address:"+address);

        addressService.addAddress(address);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    public void getAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int userId = Integer.parseInt(request.getParameter("userId"));
        List<Address> addressList = addressService.getAddressList(userId);

        Result result = Result.success(addressList);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    public void setDefault(HttpServletRequest request,HttpServletResponse response)throws Exception{
        String params = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        logger.debug(params);

        //转为Address对象(要有userId)
        Address address = JSON.parseObject(params, Address.class);

        Integer userId = address.getUserId();
        addressService.updateDefault(userId);

        address.setIsDefault(1);
        addressService.updateDefaultById(address);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    public void getDefault(HttpServletRequest request,HttpServletResponse response)throws Exception{

    }
}
