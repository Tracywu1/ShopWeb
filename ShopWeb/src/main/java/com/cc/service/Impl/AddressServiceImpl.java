package com.cc.service.Impl;

import com.cc.dao.AddressDao;
import com.cc.dao.Impl.AddressDaoImpl;
import com.cc.filter.LoginCheckFilter;
import com.cc.po.Address;
import com.cc.service.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    private final AddressDao addressDao = new AddressDaoImpl();

    @Override
    public List<Address> getAddressList() throws Exception {
        return addressDao.selectAllByUserId(LoginCheckFilter.currentUser.getId());
    }

    @Override
    public Address getAddress(Integer id) throws Exception {
        return addressDao.selectById(id);
    }

    @Override
    public void addAddress(Address address)throws Exception{
        addressDao.insert(address);
    }
}
