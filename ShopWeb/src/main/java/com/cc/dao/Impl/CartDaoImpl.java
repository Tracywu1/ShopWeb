package com.cc.dao.Impl;

import com.cc.dao.CartDao;
import com.cc.exception.MyRunTimeException;
import com.cc.exception.ResultCode;
import com.cc.po.Cart;
import com.cc.utils.CRUDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CartDaoImpl implements CartDao {
    private static final Logger logger = LoggerFactory.getLogger(CartDaoImpl.class);
    @Override
    public void delete(Integer id) throws Exception {
        String sql = "delete from tb_cart where id = ?";
        int update = CRUDUtils.update(sql, id);
        logger.debug("update:" + update);

        if (update == 0) {
            throw new MyRunTimeException(ResultCode.DELETE_FAILED);
        }
    }

    @Override
    public void insert(Cart cart) throws Exception {
        String sql = "insert into tb_cart(`id`,`userId`,`productId`,`count`,`price`,`totalPrice`,`isSelected`,`createTime`,`updateTime`) values (?,?,?,?,?,?,?,?,?)";
        int update = CRUDUtils.update(sql, cart);
        logger.debug("update:" + update);

        if (update == 0) {
            throw new MyRunTimeException(ResultCode.CREATE_FAILED);
        }
    }

    @Override
    public List<Cart> selectAll() throws Exception {
        String sql = "select * from tb_cart";
        List<Cart> carts = CRUDUtils.queryMore(sql, Cart.class, null);
        logger.debug(carts.toString());
        return carts;
    }

    @Override
    public void update(Integer count,Integer id) throws Exception {
        Object[] params ={count,id};
        String sql = "update tb_cart set `count` = ? where id = ?";
        int update = CRUDUtils.update(sql,params);
        logger.debug("update:" + update);

        if (update == 0) {
            throw new MyRunTimeException(ResultCode.UPDATE_FAILED);
        }
    }

    @Override
    public void deleteByIds(int[] ids) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("delete from tb_cart where id in (");

        for (int i = 0; i < ids.length; i++) {
            if (i == ids.length - 1) {
                sqlBuilder.append("? ");
            } else {
                sqlBuilder.append("?, ");
            }
        }

        sqlBuilder.append(")");

        int update = CRUDUtils.update(sqlBuilder.toString(), ids);
        logger.debug("update:" + update);

        if (update == 0) {
            throw new MyRunTimeException(ResultCode.DELETE_FAILED);
        }
    }
}
