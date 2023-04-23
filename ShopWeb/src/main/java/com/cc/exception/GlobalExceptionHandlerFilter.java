package com.cc.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@WebFilter("/*")
public class GlobalExceptionHandlerFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandlerFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化操作
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception ex) {
            log.error("Default Exception: ", ex);
            Result result = Result.error(ResultCode.SYSTEM_ERROR);
            servletResponse.setContentType("application/json;charset=UTF-8");
            servletResponse.getWriter().write(new ObjectMapper().writeValueAsString(result));
        }
    }

    @Override
    public void destroy() {
        // 销毁操作
    }
}