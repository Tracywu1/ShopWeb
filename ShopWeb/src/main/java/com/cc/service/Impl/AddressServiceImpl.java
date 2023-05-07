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
    public List<Address> getAddressList(Integer userId) throws Exception {
        return addressDao.selectAllByUserId(userId);
    }

    @Override
    public Address getAddress(Integer id) throws Exception {
        return addressDao.selectById(id);
    }

    @Override
    public void addAddress(Address address)throws Exception{
        addressDao.insert(address);
    }

    @Override
    public void updateDefault(Integer userId) throws Exception {
        addressDao.updateDefault(userId);
    }

    @Override
    public void updateDefaultById(Address address) throws Exception {
        addressDao.update(address);
    }

    @Override
    public Address selectDefault(Integer userId) throws Exception {
        return addressDao.selectDefault(userId);
    }
}
