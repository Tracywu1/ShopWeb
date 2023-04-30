package com.cc.service.Impl;

import com.cc.common.Constants;
import com.cc.controller.UserServlet;
import com.cc.dao.Impl.UserDaoImpl;
import com.cc.dao.UserDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.filter.LoginCheckFilter;
import com.cc.po.Order;
import com.cc.po.User;
import com.cc.service.UserService;
import com.cc.utils.RandomUsernameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author 32119
 */
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
        String username;
        do {
            // 生成随机用户名（保证其不重复）
            username = RandomUsernameGenerator.generate();
        } while (userDao.selectByUsername(username) != null);
        user.setUsername(username);
        logger.debug(username);
        int role = Constants.UserRole.ORDINARY_USERS.getNum();
        logger.debug(String.valueOf(role));
        user.setUserRole(role);
        logger.debug(String.valueOf(user));
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

