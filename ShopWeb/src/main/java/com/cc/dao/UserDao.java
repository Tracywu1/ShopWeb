package com.cc.dao;

import com.cc.po.User;


public interface UserDao {
    /**
     * 添加数据
     * @param user
     */
    void insertSelective(User user) throws Exception;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User selectById(Integer id) throws Exception;

    /**
     * 根据用户名查询
     * @param username
     * @return
     * @throws Exception
     */
    User selectByUsername(String username) throws Exception;

    /**
     * 根据电话号码查询
     * @param phoneNum
     * @return
     * @throws Exception
     */
    User selectByPhoneNum(String phoneNum)throws Exception;

    /**
     * 根据用户名和密码查询
     * @param phoneNum
     * @param password
     * @throws Exception
     * @return
     */
    User selectByPhoneNumAndPwd(String phoneNum,String password) throws Exception;

    /**
     * 修改数据
     * @param user
     */
    void updateByIdSelective(User user) throws Exception;

    /**
     * 删除用户（注销）
     * @param id
     */
    void delete(int id) throws Exception;
}
