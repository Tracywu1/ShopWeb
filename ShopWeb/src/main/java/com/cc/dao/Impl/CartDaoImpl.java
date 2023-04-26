package com.cc.dao.Impl;

import com.cc.dao.CartDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.po.Cart;
import com.cc.utils.CRUDUtils;
import com.cc.vo.CartVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
/**
 * @author 32119
 */
public class CartDaoImpl implements CartDao {
    private static final Logger logger = LoggerFactory.getLogger(CartDaoImpl.class);

    @Override
    public void delete(Integer id) throws Exception {
        String sql = "delete from tb_cart where id = ?";
        int update = CRUDUtils.update(sql, id);
        logger.debug("update:" + update);

        if (update == 0) {
            throw new MyException(ResultCode.DELETE_FAILED);
        }
    }

    @Override
    public void insert(Cart cart) throws Exception {
        String sql = "insert into tb_cart(`id`,`userId`,`productId`,`count`,`price`,`isSelected`,`createTime`,`updateTime`) values (?,?,?,?,?,?,?,?,?)";
        int update = CRUDUtils.update(sql, cart);
        logger.debug("update:" + update);

        if (update == 0) {
            throw new MyException(ResultCode.CREATE_FAILED);
        }
    }

    @Override
    public void insertSelective(Cart cart) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("insert into tb_cart");
        StringBuilder columnsBuilder = new StringBuilder("(");
        StringBuilder valuesBuilder = new StringBuilder("(");
        if (cart.getId() != null) {
            columnsBuilder.append("`id`,");
            valuesBuilder.append("?,");
        }
        if (cart.getUserId() != null) {
            columnsBuilder.append("`userId`,");
            valuesBuilder.append("?,");
        }
        if (cart.getProductId() != null) {
            columnsBuilder.append("`productId`,");
            valuesBuilder.append("?,");
        }
        if (cart.getCount() != null) {
            columnsBuilder.append("`count`,");
            valuesBuilder.append("?,");
        }
        if (cart.getIsSelected() != null) {
            columnsBuilder.append("`isSelected`,");
            valuesBuilder.append("?,");
        }
        if (cart.getCreateTime() != null) {
            columnsBuilder.append("`createTime`,");
            valuesBuilder.append("?,");
        }
        if (cart.getUpdateTime() != null) {
            columnsBuilder.append("`updateTime`,");
            valuesBuilder.append("?,");
        }

        //删掉最后一个逗号
        columnsBuilder.deleteCharAt(columnsBuilder.length() - 1);
        valuesBuilder.deleteCharAt(valuesBuilder.length() - 1);

        columnsBuilder.append(")");
        valuesBuilder.append(")");

        sqlBuilder.append(columnsBuilder);
        sqlBuilder.append(" ");
        sqlBuilder.append("values");
        sqlBuilder.append(" ");
        sqlBuilder.append(valuesBuilder);


        int count = 0;

        if (cart.getId() != null) {
            count++;
        }
        if (cart.getUserId() != null) {
            count++;
        }
        if (cart.getProductId() != null) {
            count++;
        }
        if (cart.getCount() != null) {
            count++;
        }
        if (cart.getIsSelected() != null) {
            count++;
        }
        if (cart.getCreateTime() != null) {
            count++;
        }
        if (cart.getUpdateTime() != null) {
            count++;
        }

        Object[] params = new Object[count];

        int index = 0;

        if (cart.getId() != null) {
            params[index] = cart.getId();
            index++;
        }
        if (cart.getUserId() != null) {
            params[index] = cart.getUserId();
            index++;
        }
        if (cart.getProductId() != null) {
            params[index] = cart.getProductId();
            index++;
        }
        if (cart.getCount() != null) {
            params[index] = cart.getCount();
            index++;
        }
        if (cart.getIsSelected() != null) {
            params[index] = cart.getIsSelected();
            index++;
        }
        if (cart.getCreateTime() != null) {
            params[index] = cart.getCreateTime();
            index++;
        }
        if (cart.getUpdateTime() != null) {
            params[index] = cart.getUpdateTime();
        }

        int update = CRUDUtils.update(sqlBuilder.toString(), params);
        logger.debug("update:" + update);
    }

    @Override
    public List<CartVO> selectAll(Integer userId) throws Exception {
        String sql = "select c.id as id, p.id as productId, c.userId as userId, c.count as count, c.isSelected as isSelected, p.price as price, p.name as productName, p.image as productImage from tb_cart c left join tb_product p on p.id = c.productId where c.userId = ?";
        //能否封装成功？
        List<CartVO> carts = CRUDUtils.queryMore(sql, CartVO.class, userId);
        logger.debug(carts.toString());
        return carts;
    }

    @Override
    public Cart selectByUserIdAndProductId(Integer userId, Integer productId) throws Exception {
        Object[] params = {userId, productId};
        String sql = "select * from tb_cart where userId =? and productId =?";
        Cart cart = CRUDUtils.query(sql, Cart.class, params);
        logger.debug(String.valueOf(cart));
        return cart;
    }

    @Override
    public void updateCount(Integer count, Integer id) throws Exception {
        Object[] params = {count, id};
        String sql = "update tb_cart set `count` = ? where id = ?";
        int update = CRUDUtils.update(sql, params);
        logger.debug("update:" + update);

        if (update == 0) {
            throw new MyException(ResultCode.UPDATE_FAILED);
        }
    }

    @Override
    public void updateSelect(Integer userId, Integer productId, Integer selected) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("update tb_cart set `isSelected` = ? where 1=1");
        if (userId != null) {
            sqlBuilder.append(" and userId = ?");
        }
        if (productId != null) {
            sqlBuilder.append(" and productId = ?");
        }
        String sql = sqlBuilder.toString();

        logger.debug("sql:" + sql);

        Object[] params = new Object[1];
        int index = 0;

        params[0] = selected;
        index++;
        if (userId != null) {
            params[index] = userId;
            index++;
        }
        if (productId != null) {
            params[index] = productId;
        }

        int update = CRUDUtils.update(sql, params);
        logger.debug("update:" + update);

        if (update == 0) {
            throw new MyException(ResultCode.UPDATE_FAILED);
        }
    }

}
