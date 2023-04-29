package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.Result;
import com.cc.po.Blog;
import com.cc.service.BlogService;
import com.cc.service.Impl.BlogServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

public class BlogServlet extends BaseServlet {
    private final BlogService blogService = new BlogServiceImpl();
    private static final Logger logger = LoggerFactory.getLogger(BlogServlet.class);

    /**
     * 发布动态（店铺管理员）
     * @param request
     * @param response
     * @throws Exception
     */
    public void postBlog(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String params = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        logger.debug("params:" + params);

        //转为ProductApplication对象
        Blog blog = JSON.parseObject(params, Blog.class);

        logger.debug("Blog:"+blog);

        //调用service添加
        blogService.add(blog);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 展示评论信息
     * @param request
     * @param response
     * @throws Exception
     */
    public void selectAll(HttpServletRequest request,HttpServletResponse response) throws Exception {
        int storeId = Integer.parseInt(request.getParameter("storeId"));

        //调用service查询
        List<Blog> blogs = blogService.getAllByStoreId(storeId);

        logger.debug("blogs" + blogs.toString());

        Result result = Result.success(blogs);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 删除评论（管理员）
     * @param request
     * @param response
     * @throws Exception
     */
    public void delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));

        logger.debug(String.valueOf(id));

        //调用service删除
        blogService.delete(id);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
