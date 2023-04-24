package com.cc.service;

import com.cc.po.User;
public interface UserService {
    /**
     * 删除
     * @param id
     */
    void delete(Integer id) throws Exception;


    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    User getById(Integer id) throws Exception;

    /**
     * 更新用户
     * @param user
     */
    void modify(User user) throws Exception;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    User login(String username,String password) throws Exception;

    /**
     * 用户注册
     * @param user
     */
    void register(User user) throws Exception;
}
