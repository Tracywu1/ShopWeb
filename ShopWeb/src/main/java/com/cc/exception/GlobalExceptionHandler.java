package com.cc.exception;


import com.alibaba.fastjson.JSON;
import com.cc.po.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局异常处理器
 */
public class GlobalExceptionHandler {

    //反射
    public Result ex(Exception ex, HttpServletResponse response) throws IOException {
        ex.printStackTrace();
        Result result = Result.error("对不起,操作失败,请联系管理员");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
        return result;
    }

}
