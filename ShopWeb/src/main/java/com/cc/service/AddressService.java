package com.cc.service;

import com.cc.po.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAddressList() throws Exception;

    Address getAddress(Integer id) throws Exception;
}
