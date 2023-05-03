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
}
