package com.cc.filter;

import com.cc.po.Result;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class GlobalExceptionHandlerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化操作
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
            Result result = Result.error("对不起,操作失败,请联系管理员");
            servletResponse.setContentType("application/json;charset=UTF-8");
            servletResponse.getWriter().write(new ObjectMapper().writeValueAsString(result));
        }
    }

    @Override
    public void destroy() {
        // 销毁操作
    }
}
