package com.cc.service.Impl;

import com.cc.dao.CommentDao;
import com.cc.dao.Impl.CommentDaoImpl;
import com.cc.dao.Impl.StoreDaoImpl;
import com.cc.dao.Impl.SubscribeDaoImpl;
import com.cc.dao.StoreDao;
import com.cc.dao.SubscribeDao;
import com.cc.filter.LoginCheckFilter;
import com.cc.po.Comment;
import com.cc.po.Store;
import com.cc.po.Subscribe;
import com.cc.service.CommentService;
import com.cc.service.SubscribeService;

import java.util.List;

public class SubscribeServiceImpl implements SubscribeService {
    private final SubscribeDao subscribeDao = new SubscribeDaoImpl();
    private final StoreDao storeDao = new StoreDaoImpl();

    @Override
    public List<Subscribe> getAllByProductId(Integer userId) throws Exception {
        return subscribeDao.selectByUserId(userId);
    }

    @Override
    public void add(Integer storeId) throws Exception {
        Subscribe subscribe = new Subscribe();
        subscribe.setUserId(LoginCheckFilter.currentUser.getId());
        subscribe.setStoreId(storeId);
        Store store = storeDao.selectStoreById(storeId);
        subscribe.setStoreName(store.getStoreName());
        subscribeDao.insertSelective(subscribe);

        store.setFansNum(store.getFansNum()+1);
        storeDao.updateByIdSelective(store);
    }

    @Override
    public void delete(Integer id) throws Exception {
        subscribeDao.delete(id);
    }
}
