package com.cc.filter;

import com.alibaba.fastjson.JSON;
import com.cc.common.Constants;
import com.cc.exception.Result;
import com.cc.exception.ResultCode;
import com.cc.po.User;
import com.cc.utils.JwtUtils;
import com.cc.utils.RedisUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @author 32119
 */
//@WebFilter("/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        //获取请求url。
        String url = req.getRequestURL().toString();

        //判断请求url中是否包含登录、注册相关资源，如果包含，放行。
        if (url.contains("login") || url.contains("register") || url.contains("check-code")) {
            chain.doFilter(request, response);
            return;
        }

        String jwt = req.getHeader("Authorization");
        if (jwt != null && jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        } else {
            // 没有找到授权头或授权头格式不正确
            Result result = Result.error(ResultCode.UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
            return;
        }

        // 验证 JWT 指令牌是否合法||检查 JWT 令牌是否已被加入黑名单
        Map<String, Object> claims = JwtUtils.parseJWT(jwt);
        if (claims == null||RedisUtils.hasKey(jwt)) {
            Result result = Result.error(ResultCode.UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
            return;
        }

        //放行。
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
