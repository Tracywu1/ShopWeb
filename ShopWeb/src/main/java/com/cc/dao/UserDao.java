package com.cc.dao;

import com.cc.po.User;

import java.util.List;


public interface UserDao {
    /**
     * 添加数据
     * @param user
     */
    void insert(User user);

    /**
     * 删除数据
     * @param id
     */
    void delete(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<User> selectAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User select(Integer id);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    User select(String username);

    /**
     * 根据用户名和密码查询
     * @param user
     * @return
     */
    User select(User user);

    /**
     * 修改数据
     * @param user
     */
    void update(User user);
}
