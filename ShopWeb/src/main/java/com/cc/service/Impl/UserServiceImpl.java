package com.cc.service.Impl;

import com.cc.common.Constants;
import com.cc.dao.Impl.UserDaoImpl;
import com.cc.dao.UserDao;
import com.cc.po.User;
import com.cc.service.UserService;


/**
 * @author 32119
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void delete(Integer id) throws Exception {
        userDao.delete(id);
    }

    @Override
    public User getById(Integer id) throws Exception {
        return userDao.selectById(id);
    }


    @Override
    public User login(String username, String password) throws Exception {
        return userDao.selectByUsernameAndPwd(username,password);
    }

    @Override
    public void register(User user) throws Exception {
        userDao.insertSelective(user);
    }

    @Override
    public boolean checkManager(User user) {
        return user.getUserRole().equals(Constants.UserRole.STORE_MANAGER);
    }

    @Override
    public boolean checkWebMaster(User user) {
        return user.getUserRole().equals(Constants.UserRole.WEBMASTER);
    }

    @Override
    public void updateInfo(User user) throws Exception {
        userDao.updateByIdSelective(user);
    }

}

