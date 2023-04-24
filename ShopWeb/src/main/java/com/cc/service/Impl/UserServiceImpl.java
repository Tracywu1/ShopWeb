package com.cc.service.Impl;

import com.cc.dao.Impl.UserDaoImpl;
import com.cc.dao.UserDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.po.User;
import com.cc.service.UserService;

import java.security.NoSuchAlgorithmException;


public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void delete(Integer id) throws Exception {
        userDao.delete(id);
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
    public User login(String username, String password) throws Exception {
        return userDao.select(user);
    }


    public void register(User user) throws Exception {
        userDao.insertSelective(user);
    }

}

