package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.Result;
import com.cc.po.Comment;
import com.cc.service.CommentService;
import com.cc.service.Impl.CommentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

public class CommentServlet extends BaseServlet {
    private final CommentService commentService = new CommentServiceImpl();
    private static final Logger logger = LoggerFactory.getLogger(CommentServlet.class);

    /**
     * 发布评论（用户）
     * @param request
     * @param response
     * @throws Exception
     */
    public void postComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String params = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        logger.debug("params:" + params);

        //转为ProductApplication对象(要有creatorId)
        Comment comment = JSON.parseObject(params, Comment.class);

        logger.debug("Comment:"+comment);

        //调用service添加
        commentService.add(comment);

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
        int productId = Integer.parseInt(request.getParameter("productId"));

        //调用service查询
        List<Comment> comments = commentService.getAllByProductId(productId);

        logger.debug("comments" + comments.toString());

        Result result = Result.success(comments);
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
        commentService.delete(id);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
