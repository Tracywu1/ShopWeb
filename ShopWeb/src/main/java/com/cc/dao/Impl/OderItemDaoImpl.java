package com.cc.dao.Impl;

import com.cc.dao.OrderItemDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.po.OrderItem;
import com.cc.po.Product;
import com.cc.utils.CRUDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OderItemDaoImpl implements OrderItemDao {

    private static final Logger logger = LoggerFactory.getLogger(OderItemDaoImpl.class);

    @Override
    public void insertSelective(OrderItem orderItem) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("insert into tb_order_item");
        StringBuilder columnsBuilder = new StringBuilder("(");
        StringBuilder valuesBuilder = new StringBuilder("(");
        if (orderItem.getId() != null) {
            columnsBuilder.append("`id`,");
            valuesBuilder.append("?,");
        }
        if (orderItem.getOrderNo() != null) {
            columnsBuilder.append("`orderNo`,");
            valuesBuilder.append("?,");
        }
        if (orderItem.getProductId() != null) {
            columnsBuilder.append("`productId`,");
            valuesBuilder.append("?,");
        }
        if (orderItem.getProductName() != null) {
            columnsBuilder.append("`productName`,");
            valuesBuilder.append("?,");
        }
        if (orderItem.getProductImage() != null) {
            columnsBuilder.append("`productImage`,");
            valuesBuilder.append("?,");
        }
        if (orderItem.getUnitPrice() != null) {
            columnsBuilder.append("`unitPrice`,");
            valuesBuilder.append("?,");
        }
        if (orderItem.getTotalPrice() != null) {
            columnsBuilder.append("`totalPrice`,");
            valuesBuilder.append("?,");
        }
        if (orderItem.getCount() != null) {
            columnsBuilder.append("`count`,");
            valuesBuilder.append("?,");
        }
        if (orderItem.getCreateTime() != null) {
            columnsBuilder.append("`createTime`,");
            valuesBuilder.append("?,");
        }
        if (orderItem.getUpdateTime() != null) {
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

        if (orderItem.getId() != null) {
            count++;
        }
        if (orderItem.getOrderNo() != null && !orderItem.getOrderNo().isEmpty()) {
            count++;
        }
        if (orderItem.getProductId() != null) {
            count++;
        }
        if (orderItem.getProductName() != null && !orderItem.getProductName().isEmpty()) {
            count++;
        }
        if (orderItem.getProductImage() != null && !orderItem.getProductImage().isEmpty()) {
            count++;
        }
        if (orderItem.getUnitPrice() != null) {
            count++;
        }
        if (orderItem.getTotalPrice() != null) {
            count++;
        }
        if (orderItem.getCount() != null) {
            count++;
        }
        if (orderItem.getCreateTime() != null) {
            count++;
        }
        if (orderItem.getUpdateTime() != null) {
            count++;
        }

        Object[] params = new Object[count];

        int index = 0;

        if (orderItem.getId() != null) {
            params[index] = orderItem.getId();
            index++;
        }
        if (orderItem.getOrderNo() != null) {
            params[index] = orderItem.getOrderNo();
            index++;
        }
        if (orderItem.getProductId() != null) {
            params[index] = orderItem.getProductId();
            index++;
        }
        if (orderItem.getProductName() != null && !orderItem.getProductName().isEmpty()) {
            params[index] = orderItem.getProductName();
            index++;
        }
        if (orderItem.getProductImage() != null && !orderItem.getProductImage().isEmpty()) {
            params[index] = orderItem.getProductImage();
            index++;
        }
        if (orderItem.getUnitPrice() != null) {
            params[index] = orderItem.getUnitPrice();
            index++;
        }
        if (orderItem.getTotalPrice() != null ) {
            params[index] = orderItem.getTotalPrice();
            index++;
        }
        if (orderItem.getCount() != null) {
            params[index] = orderItem.getCount();
            index++;
        }
        if (orderItem.getCreateTime() != null) {
            params[index] = orderItem.getCreateTime();
            index++;
        }
        if (orderItem.getUpdateTime() != null) {
            params[index] = orderItem.getUpdateTime();
            index++;
        }

        int update = CRUDUtils.update(sqlBuilder.toString(), params);
        logger.debug("update:" + update);
    }

    @Override
    public void delete(Integer id) throws Exception {
        String sql = "delete from tb_order_item where id = ?";
        int update = CRUDUtils.update(sql, id);
        logger.debug("update:" + update);

        if (update == 0) {
            throw new MyException(ResultCode.DELETE_FAILED);
        }
    }

    @Override
    public List<OrderItem> selectByOrderNo(String orderNo) throws Exception {
        String sql = "select * from tb_order_item where orderNo =?";
        List<OrderItem> orderItems = CRUDUtils.queryMore(sql, OrderItem.class, orderNo);
        logger.debug(String.valueOf(orderItems));
        return orderItems;
    }

    @Override
    public void updateByIdSelective(OrderItem orderItem) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("update tb_order_item");
        sqlBuilder.append(" ");
        sqlBuilder.append("set");
        if (orderItem.getOrderNo() != null) {
            sqlBuilder.append("`orderNo` = ?,");
        }
        if (orderItem.getProductId() != null) {
            sqlBuilder.append("`productId` = ?,");
        }
        if (orderItem.getProductName() != null) {
            sqlBuilder.append("`productName` = ?,");
        }
        if (orderItem.getProductImage() != null) {
            sqlBuilder.append("`productImage` = ?,");
        }
        if (orderItem.getUnitPrice() != null) {
            sqlBuilder.append("`unitPrice` = ?,");
        }
        if (orderItem.getTotalPrice() != null) {
            sqlBuilder.append("`totalPrice` = ?,");
        }
        if (orderItem.getCount() != null) {
            sqlBuilder.append("`count` = ?,");
        }
        if (orderItem.getCreateTime() != null) {
            sqlBuilder.append("`createTime` = ?,");
        }
        if (orderItem.getUpdateTime() != null) {
            sqlBuilder.append("`updateTime` = ?,");
        }

        // 删除最后一个逗号
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(" ");
        sqlBuilder.append("where id = ?");

        int count = 0;

        if (orderItem.getProductId() != null) {
            count++;
        }
        if (orderItem.getProductName() != null && !orderItem.getProductName().isEmpty()) {
            count++;
        }
        if (orderItem.getProductImage() != null && !orderItem.getProductImage().isEmpty()) {
            count++;
        }
        if (orderItem.getUnitPrice() != null) {
            count++;
        }
        if (orderItem.getTotalPrice() != null) {
            count++;
        }
        if (orderItem.getCount() != null) {
            count++;
        }

        Object[] params = new Object[count + 1];

        int index = 0;

        if (orderItem.getProductId() != null) {
            params[index] = orderItem.getProductId();
            index++;
        }
        if (orderItem.getProductName() != null && !orderItem.getProductName().isEmpty()) {
            params[index] = orderItem.getProductName();
            index++;
        }
        if (orderItem.getProductImage() != null && !orderItem.getProductImage().isEmpty()) {
            params[index] = orderItem.getProductImage();
            index++;
        }
        if (orderItem.getUnitPrice() != null) {
            params[index] = orderItem.getUnitPrice();
            index++;
        }
        if (orderItem.getTotalPrice() != null ) {
            params[index] = orderItem.getTotalPrice();
            index++;
        }
        if (orderItem.getCount() != null) {
            params[index] = orderItem.getCount();
        }

        params[params.length - 1] = orderItem.getId();

        int update = CRUDUtils.update(sqlBuilder.toString(), params);
        logger.debug("update:" + update);

        if (update == 0) {
            throw new MyException(ResultCode.UPDATE_FAILED);
        }
    }
}
