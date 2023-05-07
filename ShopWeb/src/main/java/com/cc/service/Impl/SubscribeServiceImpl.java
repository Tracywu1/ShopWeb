package com.cc.service.Impl;

import com.cc.dao.Impl.StoreDaoImpl;
import com.cc.dao.Impl.SubscribeDaoImpl;
import com.cc.dao.Impl.UserDaoImpl;
import com.cc.dao.StoreDao;
import com.cc.dao.SubscribeDao;
import com.cc.dao.UserDao;
import com.cc.po.Store;
import com.cc.po.Subscribe;
import com.cc.po.User;
import com.cc.service.SubscribeService;

import java.util.List;

public class SubscribeServiceImpl implements SubscribeService {
    private final SubscribeDao subscribeDao = new SubscribeDaoImpl();
    private final StoreDao storeDao = new StoreDaoImpl();
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public List<Subscribe> getAllByUserId(Integer userId) throws Exception {
        return subscribeDao.selectByUserId(userId);
    }

    @Override
    public Subscribe getById(Integer id) throws Exception {
        return subscribeDao.getById(id);
    }

    @Override
    public void add(Integer storeId,Integer userId) throws Exception {
        Subscribe subscribe = new Subscribe();
        subscribe.setUserId(userId);
        subscribe.setStoreId(storeId);
        Store store = storeDao.selectStoreById(storeId);
        subscribe.setStoreName(store.getStoreName());
        subscribeDao.insertSelective(subscribe);

        store.setFansNum(store.getFansNum()+1);
        storeDao.updateByIdSelective(store);

        User user = userDao.selectById(userId);
        user.setFollowCount(user.getFollowCount()+1);
        userDao.updateByIdSelective(user);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Subscribe subscribe = subscribeDao.getById(id);
        Integer storeId = subscribe.getStoreId();
        subscribeDao.delete(id);

        Store store = storeDao.selectStoreById(storeId);
        store.setFansNum(store.getFansNum()-1);
        storeDao.updateByIdSelective(store);
    }
}
