package com.cc.service.Impl;

import com.cc.common.Constants;
import com.cc.dao.CartDao;
import com.cc.dao.Impl.CartDaoImpl;
import com.cc.dao.Impl.ProductDaoImpl;
import com.cc.dao.ProductDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.filter.LoginCheckFilter;
import com.cc.po.Cart;
import com.cc.po.Product;
import com.cc.service.CartService;
import com.cc.vo.CartVO;

import java.math.BigDecimal;
import java.util.List;

public class CartServiceImpl implements CartService {
    private final CartDao cartDao = new CartDaoImpl();
    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<CartVO> list(int[] ids) throws Exception {
        return cartDao.selectByIds(ids);
    }

    @Override
    public List<CartVO> list() throws Exception {
        return cartDao.selectAll(LoginCheckFilter.currentUser.getId());
    }

    @Override
    public void add(Integer productId, Integer count) throws Exception {
        validProduct(productId, count);

        Cart cart = cartDao.selectByUserIdAndProductId(LoginCheckFilter.currentUser.getId(), productId);
        if (cart == null) {
            //这个商品之前不在购物车内，需要新增一个记录
            cart = new Cart();
            cart.setProductId(productId);
            cart.setUserId(LoginCheckFilter.currentUser.getId());
            cart.setCount(count);
            cartDao.insertSelective(cart);
        } else {
            //该商品已经在购物车内，数量相加
            count += cart.getCount();
            cartDao.updateCount(count, cart.getId());
        }
    }

    @Override
    public void validProduct(Integer productId,Integer count) throws Exception {
        Product product = productDao.selectProductById(LoginCheckFilter.currentUser.getId());
        //判断商品是否存在，商品是否上架
        if (product == null) {
            throw new MyException(ResultCode.NOT_SALE);
        }
        //判断商品库存
        if (count > product.getProductCount()) {
            throw new MyException(ResultCode.NOT_ENOUGH);
        }
    }

    @Override
    public void update(Integer count, Integer productId) throws Exception {
        validProduct(productId, count);
        Cart cart = cartDao.selectByUserIdAndProductId(LoginCheckFilter.currentUser.getId(), productId);
        cartDao.updateCount(count, cart.getId());
    }

    @Override
    public void delete(Integer productId) throws Exception {
        Cart cart = cartDao.selectByUserIdAndProductId(LoginCheckFilter.currentUser.getId(), productId);
        if (cart == null) {
            //该商品之前不在购物车内，无法更新
            throw new MyException(ResultCode.UPDATE_FAILED);
        }
        cartDao.delete(cart.getId());
    }

    @Override
    public void deleteInBatches(int[] ids) throws Exception {
        cartDao.deleteByIds(ids);
    }

    @Override
    public Integer selectCount() throws Exception {
        return cartDao.selectCount(LoginCheckFilter.currentUser.getId());
    }


}
