package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.common.Constants;
import com.cc.dao.Impl.UserDaoImpl;
import com.cc.exception.Result;
import com.cc.exception.ResultCode;
import com.cc.po.Subscribe;
import com.cc.po.User;
import com.cc.service.Impl.OrderServiceImpl;
import com.cc.service.Impl.SubscribeServiceImpl;
import com.cc.service.Impl.UserServiceImpl;
import com.cc.service.OrderService;
import com.cc.service.SubscribeService;
import com.cc.service.UserService;
import com.cc.utils.CheckCodeUtil;
import com.cc.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.cc.exception.ResultCode.*;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private final UserService userService = new UserServiceImpl();

    private final OrderService orderService = new OrderServiceImpl();

    private final SubscribeService subscribeService = new SubscribeServiceImpl();

    private static final Logger logger = LoggerFactory.getLogger(UserServlet.class);

    /**
     * 存储输入密码错误的次数
     */
    private Map<String, Integer> passwordErrorCounts = new HashMap<>();

    /**
     * 执行用户登录功能
     * @param request  请求
     * @param response 响应
     * @throws IOException IO异常
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        logger.debug("username:"+username);
        logger.debug("password:"+password);

        //获取复选框数据
        String remember = request.getParameter("remember");
        logger.debug(remember);

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

            //设置自定义 header，将令牌保存到 header 中
            response.setHeader("Authorization", "Bearer " + token);

            //判断用户是否勾选记住我
            if("true".equals(remember)){
                //勾选了，发送Cookie

                //1. 创建Cookie对象
                Cookie c_username = new Cookie("username",username);
                Cookie c_password = new Cookie("password",password);
                // 设置Cookie的存活时间
                c_username.setMaxAge( 60 * 60 * 24 * 7);
                c_password.setMaxAge( 60 * 60 * 24 * 7);
                //2. 发送
                response.addCookie(c_username);
                response.addCookie(c_password);
            }

            //保存用户信息时，不保存密码
            u.setPassword(null);
            HttpSession session = request.getSession();
            session.setAttribute(Constants.QG_MALL_USER,u);

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

        //跳转回登录界面
        request.getRequestDispatcher("/login.html").forward(request, response);

        if (errorCount != null && errorCount >= 3) {
            Result result = Result.error(ResultCode.WRONG_PASSWORD_EXCESS);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
        }
    }

    /**
     * 查看用户个人信息
     * @param request
     * @param response
     * @throws Exception
     */
    public void selectPersonalInfo(HttpServletRequest request, HttpServletResponse response)throws Exception{
        User user = userService.getById();
        user.setPassword(null);

        Result result = Result.success(user);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
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

        String params = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        logger.debug(params);

        User user = JSON.parseObject(params, User.class);

        logger.debug(String.valueOf(user));

        if(user.getPassword().isEmpty()){
            Result result = Result.error(NEED_PASSWORD);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));

            //存储错误信息到request
            request.setAttribute("register_msg", "密码为空");
            return;
        }

        if(user.getPassword().length()<8){
            Result result = Result.error(PASSWORD_TOO_SHORT);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));

            //存储错误信息到request
            request.setAttribute("register_msg", "密码过短，长度小于8");
            return;
        }

        String code = request.getParameter("checkCode");
        // 验证验证码是否正确
        String checkCode = (String) session.getAttribute("checkCodeGen");
        if (code == null || code.isEmpty() || !code.equalsIgnoreCase(checkCode)) {
            Result result = Result.error(WRONG_CHECK_CODE);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));

            //存储错误信息到request
            request.setAttribute("register_msg", "验证码错误");
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
        request.getSession().removeAttribute(Constants.QG_MALL_USER);

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
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(Constants.QG_MALL_USER);
        int id = currentUser.getId();

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

   /* *//**
     * 申请注册店铺
     * @param request
     * @param response
     * @throws Exception
     *//*
    public void RegistrationStore(HttpServletRequest request, HttpServletResponse response) throws Exception{
        userService.beManager();

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }*/

    /**
     * 确认已收货
     * @param request
     * @param response
     * @throws Exception
     */
    public void Received(HttpServletRequest request, HttpServletResponse response) throws Exception{
        orderService.received(request.getParameter("orderNo"));

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 退货
     * @param request
     * @param response
     * @throws Exception
     */
    public void returnProduct(HttpServletRequest request, HttpServletResponse response) throws Exception{
        orderService.afterSaleService(request.getParameter("orderNo"));

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 查看用户关注列表
     * @param request
     * @param response
     * @throws Exception
     */
    public void listOfFollow(HttpServletRequest request, HttpServletResponse response)throws Exception{
        List<Subscribe> subscribeList = subscribeService.getAllByUserId();

        Result result = Result.success(subscribeList);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 关注
     * @param request
     * @param response
     * @throws Exception
     */
    public void follow(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Integer storeId = Integer.valueOf(request.getParameter("id"));
        subscribeService.add(storeId);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 取消关注
     * @param request
     * @param response
     * @throws Exception
     */
    public void unfollow(HttpServletRequest request,HttpServletResponse response)throws Exception{
        Integer id = Integer.valueOf(request.getParameter("id"));
        subscribeService.delete(id);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
