package com.cc.dao;

import com.cc.po.Address;

import java.util.List;

public interface AddressDao {
    /**
     * 添加收货地址
     * @param address
     */
    void insert(Address address) throws Exception;

    /**
     * 删除收货地址
     * @param id
     */
    void delete(int id) throws Exception;

    /**
     * 修改收货地址
     * @param address
     */
    void update(Address address) throws Exception;

    /**
     * 根据收货地址ID查询收货地址信息
     * @param id
     * @return
     */
    Address selectById(Integer id) throws Exception;

    /**
     * 根据用户ID查询所有的收货地址信息
     * @param userId
     * @return
     */
    List<Address> selectAllByUserId(Integer userId) throws Exception;
}
