package com.cc.service.Impl;

import com.cc.common.Constants;
import com.cc.dao.CartDao;
import com.cc.dao.Impl.CartDaoImpl;
import com.cc.dao.Impl.ProductDaoImpl;
import com.cc.dao.ProductDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.po.Cart;
import com.cc.po.Product;
import com.cc.service.CartService;
import com.cc.vo.CartVO;

import java.math.BigDecimal;
import java.util.List;

public class CartServiceImpl implements CartService {
    private CartDao cartDao = new CartDaoImpl();
    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<CartVO> list(Integer userId) throws Exception {
        List<CartVO> cartVOS = cartDao.selectAll(userId);
        for (int i = 0; i < cartVOS.size(); i++) {
            CartVO cartVO = cartVOS.get(i);
            cartVO.setTotalPrice(new BigDecimal(cartVO.getCount()).multiply(cartVO.getPrice()));
        }
        return cartVOS;
    }

    @Override
    public void add(Integer userId, Integer productId, Integer count) throws Exception {
        validProduct(productId, count);

        Cart cart = cartDao.selectByUserIdAndProductId(userId, productId);
        if (cart == null) {
            //这个商品之前不在购物车内，需要新增一个记录
            cart = new Cart();
            cart.setProductId(productId);
            cart.setUserId(userId);
            cart.setCount(count);
            cart.setIsSelected(Constants.IsSelected.SELECTED);
            cartDao.insertSelective(cart);
        } else {
            //该商品已经在购物车内，数量相加
            count += cart.getCount();
            cartDao.updateCount(count, cart.getId());
        }
    }

    @Override
    public void validProduct(Integer userId, Integer count) throws Exception {
        Product product = productDao.select(userId);
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
    public void update(Integer count, Integer userId, Integer productId) throws Exception {
        validProduct(productId, count);
        Cart cart = cartDao.selectByUserIdAndProductId(userId, productId);
        cartDao.updateCount(count, cart.getId());
    }

    @Override
    public void delete(Integer userId, Integer productId) throws Exception {
        Cart cart = cartDao.selectByUserIdAndProductId(userId, productId);
        cartDao.delete(cart.getId());
    }

    @Override
    public void selectOrNot(Integer userId, Integer productId, Integer selected) throws Exception {
        cartDao.updateSelect(userId, productId, selected);
    }

    @Override
    public void selectAllOrNot(Integer userId, Integer selected) throws Exception {
        cartDao.updateSelect(userId, null, selected);
    }
}
