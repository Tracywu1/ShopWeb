package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.Result;
import com.cc.exception.ResultCode;
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
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException IO异常
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username.isEmpty()){
            Result result = Result.error(ResultCode.NEED_USER_NAME);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
        }
        if(password.isEmpty()){
            Result result = Result.error(ResultCode.NEED_PASSWORD);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
        }

        User u = userService.login(username, password);

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
            cookie.setMaxAge(60 * 60 * 24 * 7);//保存七天
            cookie.setPath("/");
            response.addCookie(cookie);

            //将登录成功后的user对象存储到session-->前端页面：XXX,欢迎您
            HttpSession session = request.getSession();
            session.setAttribute("username", u.getUsername());

            //登录成功，跳转页面
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/homepage");

            Result result = Result.success(u);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
            return;
        }

        // 登录失败，增加错误计数
        Integer errorCount = passwordErrorCounts.getOrDefault(username, 0) + 1;
        passwordErrorCounts.put(username, errorCount);

        //存储错误信息到request
        request.setAttribute("login_msg", "用户名或密码错误");

        //跳转回登录界面
        request.getRequestDispatcher("/login.html").forward(request, response);

        if (errorCount != null && errorCount >= 3) {
            Result result = Result.error(ResultCode.WRONG_PASSWORD_EXCESS);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
        }

    }

    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws IOException
     */
    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        //生成验证码并保存到session中
        ServletOutputStream os = response.getOutputStream();
        String checkCode = CheckCodeUtil.outputVerifyImage(100, 50, os, 4);
        session.setAttribute("checkCodeGen", checkCode);
    }

    /**
     * 用户注册
     * @param request
     * @param response
     * @throws Exception
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        User user = JSON.parseObject(requestBody, User.class);

        if(user.getPassword().isEmpty()){
            Result result = Result.error(ResultCode.NEED_PASSWORD);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
        }

        if(user.getPassword().length()<8){
            Result result = Result.error(ResultCode.PASSWORD_TOO_SHORT);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
        }

        String code = request.getParameter("checkCode");
        // 验证验证码是否正确
        String checkCode = (String) session.getAttribute("checkCodeGen");
        if (code == null || code.isEmpty() || !code.equalsIgnoreCase(checkCode)) {
            Result result = Result.error(ResultCode.WRONG_CHECK_CODE);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
            return;
        }

        userService.register(user);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 登出，清除session
     * @param request
     * @param response
     * @throws Exception
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // 清除 session
        request.getSession().invalidate();

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));

        // 重定向到登录页面
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    /**
     * 修改用户信息
     * @param request
     * @param response
     * @throws Exception
     */
    public void updateInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String newName = request.getParameter("newName");
        String newNickname =request.getParameter("newNickname");
        String newAddress =request.getParameter("newAddress");
        String newImage =request.getParameter("newImage");
        String newPhoneNum =request.getParameter("newPhoneNum");
        String newEmail = request.getParameter("newEmail");
        String newPassword = request.getParameter("newPassword");
        //获取当前用户的ID
        int id = Integer.parseInt(request.getParameter("id"));

        User user = new User();
        user.setId(id);
        user.setUsername(newName);
        user.setNickname(newNickname);
        user.setAddress(newAddress);
        user.setImage(newImage);
        user.setPhoneNum(newPhoneNum);
        user.setEmail(newEmail);
        user.setPassword(newPassword);

        // 调用service更新用户信息
        userService.updateInfo(user);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));

        // 跳转到用户信息页面
        response.sendRedirect(request.getContextPath() + "/user.html");

    }
}
