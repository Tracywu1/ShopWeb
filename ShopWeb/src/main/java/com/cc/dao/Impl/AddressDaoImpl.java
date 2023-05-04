package com.cc.dao.Impl;

import com.cc.dao.AddressDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.filter.LoginCheckFilter;
import com.cc.po.Address;
import com.cc.utils.CRUDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AddressDaoImpl implements AddressDao {
    private static final Logger logger = LoggerFactory.getLogger(AddressDaoImpl.class);

    @Override
    public void insert(Address address) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("insert into tb_address");
        StringBuilder columnsBuilder = new StringBuilder("(");
        StringBuilder valuesBuilder = new StringBuilder("(");
        if (address.getId() != null) {
            columnsBuilder.append("`id`,");
            valuesBuilder.append("?,");
        }
        if (address.getUserId() == null) {
            columnsBuilder.append("`userId`,");
            valuesBuilder.append("?,");
        }
        if (address.getReceiverName() != null) {
            columnsBuilder.append("`receiverName`,");
            valuesBuilder.append("?,");
        }
        if (address.getSex() != null) {
            columnsBuilder.append("`sex`,");
            valuesBuilder.append("?,");
        }
        if (address.getReceiverPhone() != null) {
            columnsBuilder.append("`receiverPhone`,");
            valuesBuilder.append("?,");
        }
        if (address.getProvince() != null) {
            columnsBuilder.append("`province`,");
            valuesBuilder.append("?,");
        }
        if (address.getCity() != null) {
            columnsBuilder.append("`city`,");
            valuesBuilder.append("?,");
        }
        if (address.getDistinct() != null) {
            columnsBuilder.append("`distinct`,");
            valuesBuilder.append("?,");
        }
        if (address.getAddress() != null) {
            columnsBuilder.append("`address`,");
            valuesBuilder.append("?,");
        }
        if (address.getIsDefault() != null) {
            columnsBuilder.append("`isDefault`,");
            valuesBuilder.append("?,");
        }
        if (address.getCreateTime() != null) {
            columnsBuilder.append("`createTime`,");
            valuesBuilder.append("?,");
        }
        if (address.getUpdateTime() != null) {
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

        if (address.getId() != null) {
            count++;
        }
        if (address.getUserId() == null) {
            count++;
        }
        if (address.getReceiverName() != null && !address.getReceiverName().isEmpty()) {
            count++;
        }
        if (address.getSex() == null) {
            count++;
        }
        if (address.getReceiverPhone() != null && !address.getReceiverPhone().isEmpty()) {
            count++;
        }
        if (address.getProvince() != null && !address.getProvince().isEmpty()) {
            count++;
        }
        if (address.getCity() != null && !address.getCity().isEmpty()) {
            count++;
        }
        if (address.getDistinct() != null && !address.getDistinct().isEmpty()) {
            count++;
        }
        if (address.getAddress() != null && !address.getAddress().isEmpty()) {
            count++;
        }
        if (address.getIsDefault() != null) {
            count++;
        }
        if (address.getCreateTime() != null) {
            count++;
        }
        if (address.getUpdateTime() != null) {
            count++;
        }

        Object[] params = new Object[count];

        int index = 0;

        if (address.getId() != null) {
            params[index] = address.getId();
            index++;
        }
        if (address.getUserId() == null) {
            params[index] = LoginCheckFilter.currentUser.getId();
            index++;
        }
        if (address.getReceiverName() != null && !address.getReceiverName().isEmpty()) {
            params[index] = address.getReceiverName();
            index++;
        }
        if (address.getSex() != null) {
            params[index] = address.getSex();
            index++;
        }
        if (address.getReceiverPhone() != null && !address.getReceiverPhone().isEmpty()) {
            params[index] = address.getReceiverPhone();
            index++;
        }
        if (address.getProvince() != null && !address.getProvince().isEmpty()) {
            params[index] = address.getProvince();
            index++;
        }
        if (address.getCity() != null && !address.getCity().isEmpty()) {
            params[index] = address.getCity();
            index++;
        }
        if (address.getDistinct() != null && !address.getDistinct().isEmpty()) {
            params[index] = address.getDistinct();
            index++;
        }
        if (address.getAddress() != null && !address.getAddress().isEmpty()) {
            params[index] = address.getAddress();
            index++;
        }
        if (address.getIsDefault() != null) {
            params[index] = address.getIsDefault();
            index++;
        }
        if (address.getCreateTime() != null) {
            params[index] = address.getCreateTime();
            index++;
        }
        if (address.getUpdateTime() != null) {
            params[index] = address.getUpdateTime();
        }

        int update = CRUDUtils.update(sqlBuilder.toString(), params);

        if (update == 0) {
            throw new MyException(ResultCode.CREATE_FAILED);
        }

        logger.debug("update:" + update);
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "delete from tb_address where id = ?";
        int update = CRUDUtils.update(sql, id);
        logger.debug("update:" + update);

        if (update == 0) {
            throw new MyException(ResultCode.DELETE_FAILED);
        }
    }

    @Override
    public void update(Address address) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("update tb_address");
        sqlBuilder.append(" ");
        sqlBuilder.append("set");

        if (address.getReceiverName() != null) {
            sqlBuilder.append("`receiverName` = ?,");
        }
        if (address.getSex() != null) {
            sqlBuilder.append("`sex` = ?,");
        }
        if (address.getReceiverPhone() != null) {
            sqlBuilder.append("`receiverPhone` = ?,");
        }
        if (address.getProvince() != null) {
            sqlBuilder.append("`province` = ?,");
        }
        if (address.getCity() != null) {
            sqlBuilder.append("`city` = ?,");
        }
        if (address.getDistinct() != null) {
            sqlBuilder.append("`distinct` = ?,");
        }
        if (address.getAddress() != null) {
            sqlBuilder.append("`address` = ?,");
        }
        if (address.getIsDefault() != null) {
            sqlBuilder.append("`isDefault` = ?,");
        }

        // 删除最后一个逗号
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(" ");
        sqlBuilder.append("where id = ?");

        Object[] params;
        int count = 0;

        if (address.getReceiverName() != null && !address.getReceiverName().isEmpty()) {
            count++;
        }
        if (address.getSex() != null) {
            count++;
        }
        if (address.getReceiverPhone() != null && !address.getReceiverPhone().isEmpty()) {
            count++;
        }
        if (address.getProvince() != null && !address.getProvince().isEmpty()) {
            count++;
        }
        if (address.getCity() != null && !address.getCity().isEmpty()) {
            count++;
        }
        if (address.getDistinct() != null && !address.getDistinct().isEmpty()) {
            count++;
        }
        if (address.getAddress() != null && !address.getAddress().isEmpty()) {
            count++;
        }
        if (address.getIsDefault() != null) {
            count++;
        }

        params = new Object[count + 1];

        int index = 0;

        if (address.getReceiverName() != null && !address.getReceiverName().isEmpty()) {
            params[index] = address.getReceiverName();
            index++;
        }
        if (address.getSex() != null) {
            params[index] = address.getSex();
            index++;
        }
        if (address.getReceiverPhone() != null && !address.getReceiverPhone().isEmpty()) {
            params[index] = address.getReceiverPhone();
            index++;
        }
        if (address.getProvince() != null && !address.getProvince().isEmpty()) {
            params[index] = address.getProvince();
            index++;
        }
        if (address.getCity() != null && !address.getCity().isEmpty()) {
            params[index] = address.getCity();
            index++;
        }
        if (address.getDistinct() != null && !address.getDistinct().isEmpty()) {
            params[index] = address.getDistinct();
            index++;
        }
        if (address.getAddress() != null && !address.getAddress().isEmpty()) {
            params[index] = address.getAddress();
            index++;
        }
        if (address.getIsDefault() != null) {
            params[index] = address.getIsDefault();
        }

        params[params.length - 1] = address.getId();

        int update = CRUDUtils.update(sqlBuilder.toString(), params);
        logger.debug("update:" + update);

        if (update == 0) {
            throw new MyException(ResultCode.UPDATE_FAILED);
        }
    }

    @Override
    public Address selectById(Integer id) throws Exception {
        String sql = "select * from tb_address where id =?";
        Address address = CRUDUtils.query(sql, Address.class, id);
        logger.debug(String.valueOf(address));
        return address;
    }

    @Override
    public List<Address> selectAllByUserId(Integer userId) throws Exception {
        String sql = "select * from tb_address where userId =?";
        List<Address> address = CRUDUtils.queryMore(sql, Address.class, userId);
        logger.debug(address.toString());
        return address;
    }

    @Override
    public void updateDefault(Integer userId) throws Exception {
        String sql = "update tb_address set isDefault = 0 where userId = ?";
        int update = CRUDUtils.update(sql,userId);
        logger.debug("update:"+update);

        if (update == 0) {
            throw new MyException(ResultCode.UPDATE_FAILED);
        }
    }

    @Override
    public Address selectDefault(Integer userId) throws Exception {
        String sql = "select * from tb_address where userId = ? and isDefault = 1";
        Address address = CRUDUtils.query(sql, Address.class, userId);
        logger.debug(String.valueOf(address));
        return address;
    }
}
