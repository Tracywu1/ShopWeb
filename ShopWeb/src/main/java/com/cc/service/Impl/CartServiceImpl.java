package com.cc.service.Impl;

import com.cc.dao.CartDao;
import com.cc.dao.Impl.CartDaoImpl;
import com.cc.po.Cart;
import com.cc.service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    private CartDao cartDao = new CartDaoImpl();
    @Override
    public List<Cart> getAll() throws Exception {
        return cartDao.selectAll();
    }

    @Override
    public void add(Cart cart) throws Exception {
        cartDao.insert(cart);
    }

    @Override
    public void delete(int id) throws Exception {
        cartDao.delete(id);
    }

    @Override
    public void update(int count,int id) throws Exception {
        cartDao.update(count,id);
    }

    @Override
    public void deleteInBatches(int[] ids) throws Exception {
        cartDao.deleteByIds(ids);
    }
}
