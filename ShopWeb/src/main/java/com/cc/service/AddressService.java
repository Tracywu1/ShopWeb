package com.cc.service;

import com.cc.po.Address;

import java.util.List;

public interface AddressService {
    /**
     * 获得收货地址列表
     * @return
     * @throws Exception
     */
    List<Address> getAddressList() throws Exception;

    /**
     * 根据id获得收货地址
     * @param id
     * @return
     * @throws Exception
     */
    Address getAddress(Integer id) throws Exception;

    /**
     * 添加收货地址
     * @param address
     * @throws Exception
     */
    void addAddress(Address address)throws Exception;

    /**
     * 将该用户所有地址的isDefault更新为0
     * @throws Exception
     */
    void updateDefault()throws Exception;

    /**
     * 将当前的设置的默认地址的is_default设置为1
     */
    void updateDefaultById(Address address)throws Exception;

    /**
     * 查询默认地址
     * @return
     * @throws Exception
     */
    Address selectDefault()throws Exception;
}
