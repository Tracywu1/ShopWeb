package com.cc.dao.Impl;

import com.cc.common.Constants;
import com.cc.dao.OrderDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.po.Order;
import com.cc.utils.CRUDUtils;
import com.cc.vo.OrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

    @Override
    public void insertSelective(Order order) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("insert into tb_order");
        StringBuilder columnsBuilder = new StringBuilder("(");
        StringBuilder valuesBuilder = new StringBuilder("(");
        if (order.getId() != null) {
            columnsBuilder.append("`id`,");
            valuesBuilder.append("?,");
        }
        if (order.getOrderNo() != null) {
            columnsBuilder.append("`orderNo`,");
            valuesBuilder.append("?,");
        }
        if (order.getUserId() != null) {
            columnsBuilder.append("`userId`,");
            valuesBuilder.append("?,");
        }
        if (order.getAddressId() != null) {
            columnsBuilder.append("`addressId`,");
            valuesBuilder.append("?,");
        }
        if (order.getStatus() == null) {
            columnsBuilder.append("`status`,");
            valuesBuilder.append("?,");
        }
        if (order.getTotalPrice() != null) {
            columnsBuilder.append("`totalPrice`,");
            valuesBuilder.append("?,");
        }
        if (order.getReceiverName() != null) {
            columnsBuilder.append("`receiverName`,");
            valuesBuilder.append("?,");
        }
        if (order.getReceiverPhone() != null) {
            columnsBuilder.append("`receiverPhone`,");
            valuesBuilder.append("?,");
        }
        if (order.getReceiverAddress() != null) {
            columnsBuilder.append("`receiverAddress`,");
            valuesBuilder.append("?,");
        }
        if (order.getCreateTime() != null) {
            columnsBuilder.append("`createTime`,");
            valuesBuilder.append("?,");
        }
        if (order.getUpdateTime() != null) {
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

        if (order.getId() != null) {
            count++;
        }
        if (order.getOrderNo() != null && !order.getOrderNo().isEmpty()) {
            count++;
        }
        if (order.getUserId() != null) {
            count++;
        }
        if (order.getAddressId() != null) {
            count++;
        }
        if (order.getStatus() == null) {
            count++;
        }
        if (order.getTotalPrice() != null) {
            count++;
        }
        if (order.getReceiverName() != null && !order.getReceiverName().isEmpty()) {
            count++;
        }
        if (order.getReceiverPhone() != null && !order.getReceiverPhone().isEmpty()) {
            count++;
        }
        if (order.getReceiverAddress() != null && !order.getReceiverAddress().isEmpty()) {
            count++;
        }
        if (order.getCreateTime() != null) {
            count++;
        }
        if (order.getUpdateTime() != null) {
            count++;
        }

        Object[] params = new Object[count];

        int index = 0;

        if (order.getId() != null) {
            params[index] = order.getId();
            index++;
        }
        if (order.getOrderNo() != null && !order.getOrderNo().isEmpty()) {
            params[index] = order.getOrderNo();
            index++;
        }
        if (order.getUserId() != null) {
            params[index] = order.getUserId();
            index++;
        }
        if (order.getAddressId() != null) {
            params[index] = order.getAddressId();
            index++;
        }
        //设置订单初始状态
        params[index] = Constants.OrderStatus.NOT_PAID.getNum();
        index++;
        if (order.getTotalPrice() != null) {
            params[index] = order.getTotalPrice();
            index++;
        }
        if (order.getReceiverName() != null && !order.getReceiverName().isEmpty()) {
            params[index] = order.getReceiverName();
            index++;
        }
        if (order.getReceiverPhone() != null && !order.getReceiverPhone().isEmpty()) {
            params[index] = order.getReceiverPhone();
            index++;
        }
        if (order.getReceiverAddress() != null && !order.getReceiverAddress().isEmpty()) {
            params[index] = order.getReceiverAddress();
            index++;
        }
        if (order.getCreateTime() != null) {
            params[index] = order.getCreateTime();
            index++;
        }
        if (order.getUpdateTime() != null) {
            params[index] = order.getUpdateTime();
        }

        int update = CRUDUtils.update(sqlBuilder.toString(), params);

        if(update == 0 ){
            throw new MyException(ResultCode.CREATE_FAILED);
        }

        logger.debug("update:" + update);
    }

    @Override
    public void delete(Integer id) throws Exception {
        String sql = "delete from tb_order where id = ?";
        int update = CRUDUtils.update(sql, id);
        logger.debug("update:" + update);

        if (update == 0) {
            throw new MyException(ResultCode.DELETE_FAILED);
        }
    }

    @Override
    public List<Order> selectAllForManager(Integer storeId) throws Exception {
        String sql = "select * from tb_order where storeId = ? oder by createTime desc";
        List<Order> orders = CRUDUtils.queryMore(sql, Order.class, storeId);
        logger.debug(orders.toString());
        return orders;
    }

    @Override
    public List<Order> selectNotShippedForCustomer(Integer userId) throws Exception {
        String sql = "select * from tb_order where userId = ? and status = 1 oder by createTime desc";
        List<Order> orders = CRUDUtils.queryMore(sql, Order.class, userId);
        logger.debug(orders.toString());
        return orders;
    }

    @Override
    public List<Order> selectDeliveredForCustomer(Integer userId) throws Exception {
        String sql = "select * from tb_order where userId = ? and status = 2 oder by createTime desc";
        List<Order> orders = CRUDUtils.queryMore(sql, Order.class, userId);
        logger.debug(orders.toString());
        return orders;
    }

    @Override
    public List<Order> selectReceivedForCustomer(Integer userId) throws Exception {
        String sql = "select * from tb_order where userId = ? and status = 3 oder by createTime desc";
        List<Order> orders = CRUDUtils.queryMore(sql, Order.class, userId);
        logger.debug(orders.toString());
        return orders;
    }

    @Override
    public List<Order> selectAfterSalesService(Integer userId) throws Exception {
        String sql = "select * from tb_order where userId = ? and status = 4 oder by createTime desc";
        List<Order> orders = CRUDUtils.queryMore(sql, Order.class, userId);
        logger.debug(orders.toString());
        return orders;
    }

    @Override
    public Order selectByOrderNo(String orderNo) throws Exception {
        String sql = "select * from tb_order where orderNo =?";
        Order order = CRUDUtils.query(sql, Order.class, orderNo);
        logger.debug(String.valueOf(order));
        //订单不存在，则报错
        if (order == null) {
            throw new MyException(ResultCode.NO_ORDER);
        }
        return order;
    }

    @Override
    public void updateByIdSelective(Order order) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("update tb_order");
        sqlBuilder.append(" ");
        sqlBuilder.append("set");
        if (order.getOrderNo() != null) {
            sqlBuilder.append("`orderNo` = ?,");
        }
        if (order.getUserId() != null) {
            sqlBuilder.append("`userId` = ?,");
        }
        if (order.getStatus() != null) {
            sqlBuilder.append("`status` = ?,");
        }
        if (order.getTotalPrice() != null) {
            sqlBuilder.append("`totalPrice` = ?,");
        }
        if (order.getCreateTime() != null) {
            sqlBuilder.append("`createTime` = ?,");
        }
        if (order.getUpdateTime() != null) {
            sqlBuilder.append("`updateTime` = ?,");
        }

        // 删除最后一个逗号
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(" ");
        sqlBuilder.append("where id = ?");

        Object[] params;
        int count = 0;

        if (order.getOrderNo() != null && !order.getOrderNo().isEmpty()) {
            count++;
        }
        if (order.getUserId() != null) {
            count++;
        }
        if (order.getStatus() != null) {
            count++;
        }
        if (order.getTotalPrice() != null) {
            count++;
        }

        params = new Object[count + 1];

        int index = 0;

        if (order.getOrderNo() != null && !order.getOrderNo().isEmpty()) {
            params[index] = order.getOrderNo();
            index++;
        }
        if (order.getStatus() != null ) {
            params[index] = order.getStatus();
            index++;
        }
        if (order.getTotalPrice() != null ) {
            params[index] = order.getTotalPrice();
        }

        params[params.length - 1] = order.getId();

        int update = CRUDUtils.update(sqlBuilder.toString(), params);
        logger.debug("update:" + update);

        if (update == 0) {
            throw new MyException(ResultCode.UPDATE_FAILED);
        }
    }

    @Override
    public List<OrderVO> selectByPage(Integer begin, Integer size,Integer storeId) throws Exception {
        Object[] params = {storeId,begin, size};
        String sql = "select * from tb_order where storeId = ? limit ?, ?";
        List<OrderVO> orderVOList = CRUDUtils.queryMore(sql, OrderVO.class, params);
        logger.debug(orderVOList.toString());
        return orderVOList;
    }

    @Override
    public int selectTotalCount() throws Exception {
        String sql = "select count(*) from tb_order where storeId = ?";
        int totalCount = CRUDUtils.queryCount(sql, null);
        logger.debug("totalCount:" + totalCount);
        return totalCount;
    }

    @Override
    public List<OrderVO> selectByPageAndCondition(int begin, int size, Integer storeId, String orderNo) throws Exception {
        Object[] params = {storeId,orderNo};
        String sql = "select * from tb_order where storeId = ? and orderNo = ? ";
        List<OrderVO> orderVOList = CRUDUtils.queryMore(sql, OrderVO.class, params);
        logger.debug(orderVOList.toString());
        return orderVOList;
    }

    @Override
    public int selectTotalCountByCondition(String orderNo) throws Exception {
        String sql = "select count(*) from tb_order where storeId = ? and orderNo = ? ";
        int totalCount = CRUDUtils.queryCount(sql, orderNo);
        logger.debug("totalCount:" + totalCount);
        return totalCount;
    }

}
