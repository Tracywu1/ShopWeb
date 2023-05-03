package com.cc.dao.Impl;

import com.cc.dao.StoreDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.po.Product;
import com.cc.po.Store;
import com.cc.utils.CRUDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StoreDaoImpl implements StoreDao {
    private static final Logger logger = LoggerFactory.getLogger(StoreDaoImpl.class);
    @Override
    public void insertSelective(Store store) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("insert into tb_store");
        StringBuilder columnsBuilder = new StringBuilder("(");
        StringBuilder valuesBuilder = new StringBuilder("(");
        if (store.getId() != null) {
            columnsBuilder.append("`id`,");
            valuesBuilder.append("?,");
        }
        if (store.getManagerId() != null) {
            columnsBuilder.append("`managerId`,");
            valuesBuilder.append("?,");
        }
        if (store.getStoreName() != null) {
            columnsBuilder.append("`storeName`,");
            valuesBuilder.append("?,");
        }
        if (store.getDescription() != null) {
            columnsBuilder.append("`description`,");
            valuesBuilder.append("?,");
        }
        if (store.getLogo() != null) {
            columnsBuilder.append("`logo`,");
            valuesBuilder.append("?,");
        }
        if (store.getFansNum() != null) {
            columnsBuilder.append("`fansNum`,");
            valuesBuilder.append("?,");
        }
        if (store.getCreateTime() != null) {
            columnsBuilder.append("`createTime`,");
            valuesBuilder.append("?,");
        }
        if (store.getUpdateTime() != null) {
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

        if (store.getId() != null) {
            count++;
        }
        if (store.getManagerId() != null) {
            count++;
        }
        if (store.getStoreName() != null && !store.getStoreName().isEmpty()) {
            count++;
        }
        if (store.getDescription() != null && !store.getDescription().isEmpty()) {
            count++;
        }
        if (store.getLogo() != null && !store.getLogo().isEmpty()) {
            count++;
        }
        if (store.getFansNum() != null) {
            count++;
        }
        if (store.getCreateTime() != null) {
            count++;
        }
        if (store.getUpdateTime() != null) {
            count++;
        }

        Object[] params = new Object[count];

        int index = 0;

        if (store.getId() != null) {
            params[index] = store.getId();
            index++;
        }
        if (store.getManagerId() != null) {
            params[index] = store.getManagerId();
            index++;
        }
        if (store.getStoreName() != null && !store.getStoreName().isEmpty()) {
            params[index] = store.getStoreName();
            index++;
        }
        if (store.getStoreName() != null && !store.getStoreName().isEmpty()) {
            params[index] = store.getStoreName();
            index++;
        }
        if (store.getDescription() != null && !store.getDescription().isEmpty()) {
            params[index] = store.getDescription();
            index++;
        }
        if (store.getLogo() != null && !store.getLogo().isEmpty()) {
            params[index] = store.getLogo();
            index++;
        }
        if (store.getFansNum() != null) {
            params[index] = store.getFansNum();
            index++;
        }
        if (store.getCreateTime() != null) {
            params[index] = store.getCreateTime();
            index++;
        }
        if (store.getUpdateTime() != null) {
            params[index] = store.getUpdateTime();
        }

        int update = CRUDUtils.update(sqlBuilder.toString(), params);
        logger.debug("update:" + update);
    }

    @Override
    public List<Store> selectAllStore() throws Exception {
        String sql = "select * from tb_store";
        List<Store> stores = CRUDUtils.queryMore(sql, Store.class, null);
        logger.debug(stores.toString());
        return stores;
    }

    @Override
    public Store selectStoreById(Integer id) throws Exception {
        String sql = "select * from tb_store where id =?";
        Store store = CRUDUtils.query(sql, Store.class, id);
        logger.debug(String.valueOf(store));
        return store;
    }

    @Override
    public void updateByIdSelective(Store store) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("update tb_store");
        sqlBuilder.append(" ");
        sqlBuilder.append("set");

        if (store.getStoreName() != null) {
            sqlBuilder.append("`storeName` = ?,");
        }
        if (store.getStoreName() != null) {
            sqlBuilder.append("`storeName` = ?,");
        }
        if (store.getDescription() != null) {
            sqlBuilder.append("`description` = ?,");
        }
        if (store.getLogo() != null) {
            sqlBuilder.append("`logo` = ?,");
        }
        if (store.getFansNum() != null) {
            sqlBuilder.append("`fansNum` = ?,");
        }

        if (store.getCreateTime() != null) {
            sqlBuilder.append("`createTime` = ?,");
        }
        if (store.getUpdateTime() != null) {
            sqlBuilder.append("`updateTime` = ?,");
        }

        // 删除最后一个逗号
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(" ");
        sqlBuilder.append("where id = ?");

        int count = 0;

        if (store.getStoreName() != null && !store.getStoreName().isEmpty()) {
            count++;
        }
        if (store.getDescription() != null && !store.getDescription().isEmpty()) {
            count++;
        }
        if (store.getLogo() != null && !store.getLogo().isEmpty()) {
            count++;
        }

        Object[] params = new Object[count + 1];

        int index = 0;

        if (store.getStoreName() != null && !store.getStoreName().isEmpty()) {
            params[index] = store.getStoreName();
            index++;
        }
        if (store.getDescription() != null && !store.getDescription().isEmpty()) {
            params[index] = store.getDescription();
            index++;
        }
        if (store.getLogo() != null && !store.getLogo().isEmpty()) {
            params[index] = store.getLogo();
        }

        params[params.length - 1] = store.getId();

        int update = CRUDUtils.update(sqlBuilder.toString(), params);
        logger.debug("update:" + update);

        if (update == 0) {
            throw new MyException(ResultCode.UPDATE_FAILED);
        }
    }
}
