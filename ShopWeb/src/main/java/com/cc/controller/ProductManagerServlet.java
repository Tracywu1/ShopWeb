package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.po.PageBean;
import com.cc.po.Product;
import com.cc.exception.Result;
import com.cc.service.Impl.ProductServiceImpl;
import com.cc.service.ProductService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author 32119
 */
@WebServlet("/product/manager/*")
public class ProductManagerServlet extends BaseServlet {
    private static final Logger logger = LoggerFactory.getLogger(ProductManagerServlet.class);
    private static final String FILE_UPLOAD_DIR = "/path/to/file/upload/dir/";
    private final ProductService productService = new ProductServiceImpl();

    /**
     * 查询所有
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //调用service查询
        List<Product> products = productService.getAll();

        logger.debug("products" + products.toString());

        Result result = Result.success(products);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 添加数据
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String params = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        logger.debug("params" + params);

        //转为Product对象
        Product product = JSON.parseObject(params, Product.class);

        //调用service添加
        productService.add(product);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 下架商品
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));

        logger.debug(String.valueOf(id));

        //调用service添加
        productService.delete(id);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 批量删除
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void deleteInBatches(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String params = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        logger.debug(params);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(params);
        int[] ids = mapper.treeToValue(node.get("ids"), int[].class);

        logger.debug(Arrays.toString(ids));

        //调用service批量删除
        productService.deleteInBatches(ids);

        //响应成功标识
        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 修改
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String params = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        logger.debug(params);

        //转为Product对象
        Product product = JSON.parseObject(params, Product.class);

        //调用service添加
        productService.update(product);

        Result result = Result.success();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));

    }

    /**
     * 分页查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        logger.debug("currentPage" + currentPage);
        logger.debug("pageSize" + pageSize);

        // 调用service查询
        PageBean<Product> pageBean = productService.selectByPage(currentPage, pageSize);

        //响应成功标识
        Result result = Result.success(pageBean);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 分页条件查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        logger.debug("currentPage:" + currentPage);
        logger.debug("pageSize:" + pageSize);

        String productName = request.getParameter("productName");
        String storeName = request.getParameter("storeName");

        logger.debug("productName:" + productName);
        logger.debug("storeName:" + storeName);

        // 调用service查询
        PageBean<Product> pageBean = productService.selectByPageAndCondition(currentPage, pageSize, productName, storeName);

        //响应成功标识
        Result result = Result.success(pageBean);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 上传文件
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        // 获取上传的文件数据(getPart:用于获取使用multipart/form-data格式传递的http请求的请求体，通常用于获取上传文件。)
        Part filePart = request.getPart("file");
        // 获取文件名
        String fileName = filePart.getSubmittedFileName();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        if (!".jpg".equalsIgnoreCase(suffixName) && !".png".equalsIgnoreCase(suffixName)) {
            Result result = Result.error(ResultCode.WRONG_FILE_TYPE);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
            return;
        }

        if (filePart.getSize() == 0) {
            Result result = Result.error(ResultCode.EMPTY_FILE);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
            return;
        }
        try {
            // 生成文件名称UUID
            UUID uuid = UUID.randomUUID();
            String newFileName = uuid + suffixName;
            // 创建文件
            File fileDirectory = new File(FILE_UPLOAD_DIR);
            File destFile = new File(FILE_UPLOAD_DIR + newFileName);
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new MyException(ResultCode.MKDIR_FAILED);
                }
            }
            // 获取文件内容输入流
            inputStream = filePart.getInputStream();
            // 创建文件输出流
            outputStream = Files.newOutputStream(destFile.toPath());
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                // 写入文件内容
                outputStream.write(buffer, 0, bytesRead);
            }
            // 返回上传成功的文件URL
            String fileUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "")
                    + "/images/" + newFileName;
            //响应成功标识
            Result<String> result = Result.success(fileUrl);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException e) {
            Result result = Result.error(ResultCode.UPLOAD_FAILED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}

