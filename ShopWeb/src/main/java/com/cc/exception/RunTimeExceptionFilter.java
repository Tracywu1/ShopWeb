package com.cc.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

//@WebFilter("/*")
public class RunTimeExceptionFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandlerFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (MyRunTimeException ex) {
            log.error("RuntimeException: ", ex);
            Result result = Result.error(ex.getCode(),ex.getMsg());
            servletResponse.setContentType("application/json;charset=UTF-8");
            servletResponse.getWriter().write(new ObjectMapper().writeValueAsString(result));
        }
    }

    @Override
    public void destroy() {

    }
}
