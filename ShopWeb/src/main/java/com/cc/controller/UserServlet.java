package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.Result;
import com.cc.po.User;
import com.cc.service.Impl.UserServiceImpl;
import com.cc.service.UserService;
import com.cc.utils.CheckCodeUtil;
import com.cc.utils.JwtUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    /**
     * 存储输入密码错误的次数
     */
    private Map<String, Integer> passwordErrorCounts = new HashMap<>();

    /**
     * 执行用户登录功能
     * @param request 请求
     * @param response 响应
     * @throws IOException IO异常
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User u =userService.login(username,password);

        //登录成功,生成令牌,下发令牌
        if (u != null) {
            // 如果登录成功，则重置错误计数
            passwordErrorCounts.remove(username);
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getId());
            claims.put("username", u.getUsername());
            //jwt包含了当前登录的用户信息
            String token = JwtUtils.generateJwt(claims);

            //设置cookie，将令牌保存到cookie中，不要使用localStorage，因为它容易受到XSS攻击
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);

            //将登录成功后的user对象存储到session-->前端页面：XXX,欢迎您
            HttpSession session=request.getSession();
            session.setAttribute("username",username);

            //登录成功，跳转页面
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath+"/homepage");

            Result result = Result.success("");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
            return;
        }

        // 登录失败，增加错误计数
        Integer errorCount = passwordErrorCounts.getOrDefault(username, 0) + 1;
        passwordErrorCounts.put(username, errorCount);

        //存储错误信息到request
        request.setAttribute("login_msg","用户名或密码错误");

        //跳转回登录界面
        request.getRequestDispatcher("/login.html").forward(request,response);

        if (errorCount != null && errorCount >= 3) {
            Result result = Result.error(r);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
        }

    }

    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session =request.getSession();
        //生成验证码并保存到session中
        ServletOutputStream os = response.getOutputStream();
        String checkCode = CheckCodeUtil.outputVerifyImage(100, 50, os, 4);
        session.setAttribute("checkCodeGen",checkCode);
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws Exception {


        HttpSession session = request.getSession();

        String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        User user = JSON.parseObject(requestBody, User.class);

        String code = request.getParameter("checkCode");
        // 验证验证码是否正确
        String checkCode = (String) session.getAttribute("checkCodeGen");
        if (code == null || code.isEmpty() || !code.equalsIgnoreCase(checkCode)) {
            Result result = Result.error("验证码错误");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
            return;
        }
        if (userService.register(user)) {
            Result result = Result.success();
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
            return;
        }
        Result result = Result.error("用户名已经存在，请更换");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }


    public Result delete(Integer id) throws Exception {
        userService.delete(id);
        return Result.success();
    }

    public Result getById( Integer id) throws Exception {
        User user = userService.getById(id);
        return Result.success(user);
    }

    public Result modify( User user) throws Exception {
        userService.modify(user);
        return Result.success();
    }
}
