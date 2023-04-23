package com.cc.service.Impl;

import com.cc.dao.Impl.UserDaoImpl;
import com.cc.dao.UserDao;
import com.cc.po.PageBean;
import com.cc.po.Product;
import com.cc.po.User;
import com.cc.service.UserService;

import java.util.List;


public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void delete(Integer id) throws Exception {
        userDao.delete(id);
    }

    @Override
    public void save(User user) throws Exception {
        userDao.insert(user);
    }

    @Override
    public User getById(Integer id) throws Exception {
        return userDao.select(id);
    }

    @Override
    public void modify(User user) throws Exception {
        userDao.update(user);
    }

    @Override
    public User login(User user) throws Exception {return userDao.select(user);}


    public boolean register(User user) throws Exception {
        if (userDao.select(user.getUsername()) != null) {
            return false;
        }
        userDao.insert(user);
        return true;
    }

    @Override
    public void deleteByIds(int[] ids) {

    }

    @Override
    public PageBean<Product> selectByPage(int currentPage, int pageSize) {
        return null;
    }

    @Override
    public PageBean<Product> selectByPageAndCondition(int currentPage, int pageSize, User user) {
        return null;
    }


}

