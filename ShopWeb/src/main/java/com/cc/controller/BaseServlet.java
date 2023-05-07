package com.cc.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 替换HttpServlet,根据请求的最后一段路径来进行方法分发
 * @author 32119
 */
@WebServlet("/baseServlet")
public class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        try {
            //1. 获取请求路径
            String uri = req.getRequestURI();

            //2. 获取最后一段路径，方法名
            int index = uri.lastIndexOf('/');
            String methodName = uri.substring(index + 1);

            //3. 执行方法
            //3.1 获取XXXServlet字节码对象 Class
            Class<? extends BaseServlet> cls = this.getClass();

            //3.2 获取方法 Method对象
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            //3.3执行方法
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
