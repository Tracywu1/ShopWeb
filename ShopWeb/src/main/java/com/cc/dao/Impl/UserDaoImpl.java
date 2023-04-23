package com.cc.dao.Impl;

import com.cc.dao.UserDao;
import com.cc.po.User;
import com.cc.utils.CRUDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    @Override
    public void insert(User user) throws Exception {
        Object[] params = {user.getUsername(), user.getPassword()};
        String sql = "insert into tb_user(username,password) values(?,?)";
        int update = CRUDUtils.update(sql, params);
        logger.debug("update:"+update);
    }

    @Override
    public void delete(Integer id) throws Exception {
        String sql = "delete from tb_user where id = ?";
        int update = CRUDUtils.update(sql, id);
        logger.debug("update:"+update);
    }

    @Override
    public List<User> selectAll() throws Exception {
        String sql = "select * from tb_user";
        List<User> users = CRUDUtils.queryMore(sql, User.class,null);
        logger.debug(users.toString());
        return users;
    }


    @Override
    public User select(Integer id) throws Exception {
        String sql = "select id,username,password from tb_user where id =?";
        User user = CRUDUtils.query(sql, User.class, id);
        logger.debug(String.valueOf(user));
        return user;
    }

    @Override
    public User select(String username) throws Exception {
        String sql = "select id,username,password from tb_user where username =?";
        User user = CRUDUtils.query(sql, User.class, username);
        logger.debug(String.valueOf(user));
        return user;
    }

    @Override
    public User select(User user) throws Exception {
        Object[] params = {user.getUsername(), user.getPassword()};
        String sql = "select id,username,password from tb_user where username = ? and password = ?";
        User user1 = CRUDUtils.query(sql, User.class, params);
        logger.debug(String.valueOf(user1));
        return user1;
    }


    @Override
    public void update(User user) throws Exception {
        Object[] params = {user.getUsername(),user.getPassword(),user.getId()};
        String sql = "update tb_user set username = ?,password = ? where id = ?";
        int update = CRUDUtils.update(sql, params);
        logger.debug(String.valueOf(update));
    }


}
