package com.cc.service.Impl;

import com.cc.dao.Impl.StoreDaoImpl;
import com.cc.dao.StoreDao;
import com.cc.po.Store;
import com.cc.service.StoreService;

public class StoreServiceImpl implements StoreService {
    private final StoreDao storeDao = new StoreDaoImpl();
    @Override
    public void add(Store store) throws Exception {
        storeDao.insertSelective(store);
    }
}
