package com.cc.service;

import com.cc.po.PageBean;
import com.cc.po.Product;
import com.cc.po.User;

import java.util.List;

public interface UserService {
    /**
     * 删除
     * @param id
     */
    void delete(Integer id) throws Exception;

    /**
     * 新增用户
     * @param user
     */
    void save(User user) throws Exception;

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
     * @param user
     * @return
     */
    User login(User user) throws Exception;

    /**
     * 用户注册
     * @param user
     * @return
     */
    boolean register(User user) throws Exception;

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds( int[] ids);

    /**
     * 分页查询
     * @param currentPage  当前页码
     * @param pageSize   每页展示条数
     * @return
     */
    PageBean<Product> selectByPage(int currentPage, int pageSize);

    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param user
     * @return
     */
    PageBean<Product>  selectByPageAndCondition(int currentPage,int pageSize,User user);
}
