package com.cc.service.Impl;

import com.cc.dao.CommentDao;
import com.cc.dao.Impl.CommentDaoImpl;
import com.cc.filter.LoginCheckFilter;
import com.cc.po.Comment;
import com.cc.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao = new CommentDaoImpl();

    @Override
    public List<Comment> getAllByProductId(Integer productId) throws Exception {
        return commentDao.selectByProductId(productId);
    }

    @Override
    public void add(Comment comment) throws Exception {
        comment.setCreatorId(LoginCheckFilter.currentUser.getId());
        comment.setImage(LoginCheckFilter.currentUser.getImage());
        commentDao.insertSelective(comment);
    }

    @Override
    public void delete(Integer id) throws Exception {
        commentDao.delete(id);
    }
}
