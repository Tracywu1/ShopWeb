package com.cc.dao.Impl;

import com.cc.dao.BlogDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.po.Blog;
import com.cc.po.Comment;
import com.cc.utils.CRUDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BlogDaoImpl implements BlogDao {
    private static final Logger logger = LoggerFactory.getLogger(BlogDaoImpl.class);
    @Override
    public List<Blog> selectByStoreId(Integer storeId) throws Exception {
        String sql ="select * from tb_blog where storeId = ?";
        List<Blog> blogList = CRUDUtils.queryMore(sql,Blog.class,storeId);
        logger.debug("blogList:"+blogList);
        return blogList;
    }

    @Override
    public void delete(Integer id) throws Exception {
        String sql ="delete from tb_blog where id = ?";
        int update = CRUDUtils.update(sql,id);

        if (update == 0) {
            throw new MyException(ResultCode.DELETE_FAILED);
        }

        logger.debug("update:"+update);
    }

    @Override
    public void insertSelective(Blog blog) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("insert into tb_blog");
        StringBuilder columnsBuilder = new StringBuilder("(");
        StringBuilder valuesBuilder = new StringBuilder("(");
        if (blog.getId() != null) {
            columnsBuilder.append("`id`,");
            valuesBuilder.append("?,");
        }
        if (blog.getStoreId() != null) {
            columnsBuilder.append("`storeId`,");
            valuesBuilder.append("?,");
        }
        if (blog.getContent() != null) {
            columnsBuilder.append("`content`,");
            valuesBuilder.append("?,");
        }
        if (blog.getCreateTime() != null) {
            columnsBuilder.append("`createTime`,");
            valuesBuilder.append("?,");
        }


        //删掉最后一个逗号
        columnsBuilder.deleteCharAt(columnsBuilder.length() - 1);
        valuesBuilder.deleteCharAt(valuesBuilder.length() - 1);

        columnsBuilder.append(")");
        valuesBuilder.append(")");

        sqlBuilder.append(columnsBuilder);
        sqlBuilder.append(" ");
        sqlBuilder.append("values");
        sqlBuilder.append(" ");
        sqlBuilder.append(valuesBuilder);

        int count = 0;

        if (blog.getId() != null) {
            count++;
        }
        if (blog.getStoreId() != null) {
            count++;
        }
        if (blog.getContent() != null && !blog.getContent().isEmpty()) {
            count++;
        }
        if (blog.getCreateTime() != null) {
            count++;
        }

        Object[] params = new Object[count + 1];

        int index = 0;

        if (blog.getId() != null) {
            params[index] = blog.getId();
            index++;
        }
        if (blog.getStoreId() != null) {
            params[index] = blog.getStoreId();
            index++;
        }
        if (blog.getContent() != null && !blog.getContent().isEmpty()) {
            params[index] = blog.getContent();
            index++;
        }
        if (blog.getCreateTime() != null) {
            params[index] = blog.getCreateTime();
        }

        int update = CRUDUtils.update(sqlBuilder.toString(), params);

        if (update == 0) {
            throw new MyException(ResultCode.INSERT_FAILED);
        }

        logger.debug("update:" + update);
    }

}
