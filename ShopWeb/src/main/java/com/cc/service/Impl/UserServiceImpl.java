package com.cc.service.Impl;

import com.cc.common.Constants;
import com.cc.dao.Impl.UserDaoImpl;
import com.cc.dao.UserDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.filter.LoginCheckFilter;
import com.cc.po.Order;
import com.cc.po.User;
import com.cc.service.UserService;


/**
 * @author 32119
 */
public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public void delete(Integer id) throws Exception {
        userDao.delete(id);
    }

    @Override
    public User getById() throws Exception {
        return userDao.selectById(LoginCheckFilter.currentUser.getId());
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

    @Override
    public void beManager() throws Exception {
        User user = userDao.selectById(LoginCheckFilter.currentUser.getId());
        if (user.getUserRole() == Constants.UserRole.ORDINARY_USERS.getNum()) {
            user.setUserRole(Constants.UserRole.STORE_MANAGER.getNum());
            userDao.updateByIdSelective(user);
        } else {
            throw new MyException(ResultCode.WRONG_USER_ROLE);
        }
    }

}

