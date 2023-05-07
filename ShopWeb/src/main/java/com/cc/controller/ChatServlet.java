package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.Result;
import com.cc.service.ChatService;
import com.cc.service.Impl.ChatServiceImpl;
import com.cc.vo.ChatVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ChatServlet extends BaseServlet{
    private final ChatService chatService = new ChatServiceImpl();
    private static final Logger logger = LoggerFactory.getLogger(ChatServlet.class);

    /**
     * 获取聊天列表
     * @param request
     * @param response
     * @throws Exception
     */
    public void list(HttpServletRequest request, HttpServletResponse response) throws Exception{
        int userId = Integer.parseInt(request.getParameter("userId"));
        List<ChatVO> chatVOList = chatService.list(userId);

        Result result = Result.success(chatVOList);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }


}
