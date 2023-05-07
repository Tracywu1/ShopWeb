package com.cc.service.Impl;

import com.cc.dao.BlogDao;
import com.cc.dao.CommentDao;
import com.cc.dao.Impl.BlogDaoImpl;
import com.cc.dao.Impl.CommentDaoImpl;
import com.cc.filter.LoginCheckFilter;
import com.cc.po.Blog;
import com.cc.po.Comment;
import com.cc.service.BlogService;
import com.cc.service.CommentService;

import java.util.List;

public class BlogServiceImpl implements BlogService {
    private final BlogDao blogDao = new BlogDaoImpl();

    @Override
    public List<Blog> getAllByStoreId(Integer storeId) throws Exception {
        return blogDao.selectByStoreId(storeId);
    }

    @Override
    public void add(Blog blog) throws Exception {
        blogDao.insertSelective(blog);
    }

    @Override
    public void delete(Integer id) throws Exception {
        blogDao.delete(id);
    }
}
