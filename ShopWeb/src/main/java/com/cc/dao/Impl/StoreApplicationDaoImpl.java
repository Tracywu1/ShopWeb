package com.cc.dao.Impl;

import com.cc.common.Constants;
import com.cc.dao.ProductApplicationDao;
import com.cc.dao.StoreApplicationDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.po.ProductApplication;
import com.cc.po.StoreApplication;
import com.cc.utils.CRUDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StoreApplicationDaoImpl implements StoreApplicationDao {
    private static final Logger logger = LoggerFactory.getLogger(StoreApplicationDao.class);

    @Override
    public void insertApplicationSelective(StoreApplication storeApplication) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("insert into tb_store_application");
        StringBuilder columnsBuilder = new StringBuilder("(");
        StringBuilder valuesBuilder = new StringBuilder("(");
        if (storeApplication.getId() != null) {
            columnsBuilder.append("`id`,");
            valuesBuilder.append("?,");
        }
        if (storeApplication.getUserId() != null) {
            columnsBuilder.append("`storeId`,");
            valuesBuilder.append("?,");
        }
        if (storeApplication.getStoreName() != null) {
            columnsBuilder.append("`storeName`,");
            valuesBuilder.append("?,");
        }
        if (storeApplication.getStoreName() != null) {
            columnsBuilder.append("`storeName`,");
            valuesBuilder.append("?,");
        }
        if (storeApplication.getDescription() != null) {
            columnsBuilder.append("`description`,");
            valuesBuilder.append("?,");
        }
        if (storeApplication.getStatus() != null) {
            columnsBuilder.append("`status`,");
            valuesBuilder.append("?,");
        }
        if (storeApplication.getLogo() != null) {
            columnsBuilder.append("`logo`,");
            valuesBuilder.append("?,");
        }
        if (storeApplication.getCreateTime() != null) {
            columnsBuilder.append("`createTime`,");
            valuesBuilder.append("?,");
        }
        if (storeApplication.getUpdateTime() != null) {
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

        if (storeApplication.getId() != null) {
            count++;
        }
        if (storeApplication.getUserId() != null) {
            count++;
        }
        if (storeApplication.getStoreName() != null && !storeApplication.getStoreName().isEmpty()) {
            count++;
        }
        if (storeApplication.getStoreName() != null && !storeApplication.getStoreName().isEmpty()) {
            count++;
        }
        if (storeApplication.getDescription() != null && !storeApplication.getDescription().isEmpty()) {
            count++;
        }
        if (storeApplication.getStatus() != null) {
            count++;
        }
        if (storeApplication.getLogo() != null && !storeApplication.getLogo().isEmpty()) {
            count++;
        }
        if (storeApplication.getCreateTime() != null) {
            count++;
        }
        if (storeApplication.getUpdateTime() != null) {
            count++;
        }

        Object[] params = new Object[count];

        int index = 0;

        if (storeApplication.getId() != null) {
            params[index] = storeApplication.getId();
            index++;
        }
        if (storeApplication.getUserId() != null) {
            params[index] = storeApplication.getUserId();
            index++;
        }
        if (storeApplication.getStoreName() != null && !storeApplication.getStoreName().isEmpty()) {
            params[index] = storeApplication.getStoreName();
            index++;
        }
        if (storeApplication.getStoreName() != null && !storeApplication.getStoreName().isEmpty()) {
            params[index] = storeApplication.getStoreName();
            index++;
        }
        if (storeApplication.getDescription() != null && !storeApplication.getDescription().isEmpty()) {
            params[index] = storeApplication.getDescription();
            index++;
        }

        //设置申请状态
        params[index] = Constants.ApplyStatus.TO_BE_PROCESSED.getNum();
        index++;

        if (storeApplication.getLogo() != null && !storeApplication.getLogo().isEmpty()) {
            params[index] = storeApplication.getLogo();
            index++;
        }

        if (storeApplication.getCreateTime() != null) {
            params[index] = storeApplication.getCreateTime();
            index++;
        }
        if (storeApplication.getUpdateTime() != null) {
            params[index] = storeApplication.getUpdateTime();
        }

        int update = CRUDUtils.update(sqlBuilder.toString(), params);
        logger.debug("update:" + update);
    }

    @Override
    public List<StoreApplication> selectAllStoreApplication() throws Exception {
        String sql = "select * from tb_store_application";
        List<StoreApplication> storeApplications = CRUDUtils.queryMore(sql, StoreApplication.class, null);
        logger.debug(storeApplications.toString());
        return storeApplications;
    }

    @Override
    public StoreApplication selectApplicationById(Integer id) throws Exception {
        String sql = "select * from tb_store_application where id =?";
        StoreApplication storeApplication = CRUDUtils.query(sql, StoreApplication.class, id);
        logger.debug(String.valueOf(storeApplication));
        return storeApplication;
    }

    @Override
    public void updateByIdSelective(StoreApplication storeApplication) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("update tb_store");
        sqlBuilder.append(" ");
        sqlBuilder.append("set");

        if (storeApplication.getStoreName() != null) {
            sqlBuilder.append("`storeName` = ?,");
        }
        if (storeApplication.getStoreName() != null) {
            sqlBuilder.append("`storeName` = ?,");
        }
        if (storeApplication.getDescription() != null) {
            sqlBuilder.append("`description` = ?,");
        }
        if (storeApplication.getStatus() != null) {
            sqlBuilder.append("`status` = ?,");
        }
        if (storeApplication.getLogo() != null) {
            sqlBuilder.append("`logo` = ?,");
        }
        if (storeApplication.getCreateTime() != null) {
            sqlBuilder.append("`createTime` = ?,");
        }
        if (storeApplication.getUpdateTime() != null) {
            sqlBuilder.append("`updateTime` = ?,");
        }

        // 删除最后一个逗号
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(" ");
        sqlBuilder.append("where id = ?");

        int count = 0;

        if (storeApplication.getStoreName() != null && !storeApplication.getStoreName().isEmpty()) {
            count++;
        }
        if (storeApplication.getDescription() != null && !storeApplication.getDescription().isEmpty()) {
            count++;
        }
        if (storeApplication.getStatus() != null) {
            count++;
        }
        if (storeApplication.getLogo() != null && !storeApplication.getLogo().isEmpty()) {
            count++;
        }


        Object[] params = new Object[count + 1];

        int index = 0;

        if (storeApplication.getStoreName() != null && !storeApplication.getStoreName().isEmpty()) {
            params[index] = storeApplication.getStoreName();
            index++;
        }
        if (storeApplication.getStoreName() != null && !storeApplication.getStoreName().isEmpty()) {
            params[index] = storeApplication.getStoreName();
            index++;
        }
        if (storeApplication.getDescription() != null && !storeApplication.getDescription().isEmpty()) {
            params[index] = storeApplication.getDescription();
            index++;
        }
        if (storeApplication.getStatus() != null) {
            params[index] = storeApplication.getStatus();
        }
        if (storeApplication.getLogo() != null && !storeApplication.getLogo().isEmpty()) {
            params[index] = storeApplication.getLogo();
        }


        params[params.length - 1] = storeApplication.getId();

        int update = CRUDUtils.update(sqlBuilder.toString(), params);
        logger.debug("update:" + update);

        if (update == 0) {
            throw new MyException(ResultCode.UPDATE_FAILED);
        }
    }


}
