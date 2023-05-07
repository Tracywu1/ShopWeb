package com.cc.dao.Impl;

import com.cc.common.Constants;
import com.cc.dao.ProductApplicationDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.po.ProductApplication;
import com.cc.utils.CRUDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductApplicationDaoImpl implements ProductApplicationDao {
    private static final Logger logger = LoggerFactory.getLogger(ProductApplicationDaoImpl.class);

    @Override
    public void insertApplicationSelective(ProductApplication productApplication) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("insert into tb_product_application");
        StringBuilder columnsBuilder = new StringBuilder("(");
        StringBuilder valuesBuilder = new StringBuilder("(");
        if (productApplication.getId() != null) {
            columnsBuilder.append("`id`,");
            valuesBuilder.append("?,");
        }
        if (productApplication.getStoreId() != null) {
            columnsBuilder.append("`storeId`,");
            valuesBuilder.append("?,");
        }
        if (productApplication.getProductName() != null) {
            columnsBuilder.append("`productName`,");
            valuesBuilder.append("?,");
        }
        if (productApplication.getStoreName() != null) {
            columnsBuilder.append("`storeName`,");
            valuesBuilder.append("?,");
        }
        if (productApplication.getDescription() != null) {
            columnsBuilder.append("`description`,");
            valuesBuilder.append("?,");
        }
        if (productApplication.getImage() != null) {
            columnsBuilder.append("`image`,");
            valuesBuilder.append("?,");
        }
        if (productApplication.getPrice() != null) {
            columnsBuilder.append("`price`,");
            valuesBuilder.append("?,");
        }
        if (productApplication.getProductCount() != null) {
            columnsBuilder.append("`productCount`,");
            valuesBuilder.append("?,");
        }
        if (productApplication.getStatus() == null) {
            columnsBuilder.append("`status`,");
            valuesBuilder.append("?,");
        }
        if (productApplication.getCreateTime() != null) {
            columnsBuilder.append("`createTime`,");
            valuesBuilder.append("?,");
        }
        if (productApplication.getUpdateTime() != null) {
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

        if (productApplication.getId() != null) {
            count++;
        }
        if (productApplication.getStoreId() != null) {
            count++;
        }
        if (productApplication.getProductName() != null && !productApplication.getProductName().isEmpty()) {
            count++;
        }
        if (productApplication.getStoreName() != null && !productApplication.getStoreName().isEmpty()) {
            count++;
        }
        if (productApplication.getDescription() != null && !productApplication.getDescription().isEmpty()) {
            count++;
        }
        if (productApplication.getImage() != null && !productApplication.getImage().isEmpty()) {
            count++;
        }
        if (productApplication.getPrice() != null) {
            count++;
        }
        if (productApplication.getProductCount() != null) {
            count++;
        }
        if (productApplication.getStatus() == null) {
            count++;
        }
        if (productApplication.getCreateTime() != null) {
            count++;
        }
        if (productApplication.getUpdateTime() != null) {
            count++;
        }

        Object[] params = new Object[count];

        int index = 0;

        if (productApplication.getId() != null) {
            params[index] = productApplication.getId();
            index++;
        }
        if (productApplication.getStoreId() != null) {
            params[index] = productApplication.getStoreId();
            index++;
        }
        if (productApplication.getProductName() != null && !productApplication.getProductName().isEmpty()) {
            params[index] = productApplication.getProductName();
            index++;
        }
        if (productApplication.getStoreName() != null && !productApplication.getStoreName().isEmpty()) {
            params[index] = productApplication.getStoreName();
            index++;
        }
        if (productApplication.getDescription() != null && !productApplication.getDescription().isEmpty()) {
            params[index] = productApplication.getDescription();
            index++;
        }
        if (productApplication.getImage() != null && !productApplication.getImage().isEmpty()) {
            params[index] = productApplication.getImage();
            index++;
        }
        if (productApplication.getPrice() != null) {
            params[index] = productApplication.getPrice();
            index++;
        }
        if (productApplication.getProductCount() != null) {
            params[index] = productApplication.getProductCount();
            index++;
        }

        //设置申请状态
        params[index] = Constants.ApplyStatus.TO_BE_PROCESSED.getNum();
        index++;

        if (productApplication.getCreateTime() != null) {
            params[index] = productApplication.getCreateTime();
            index++;
        }
        if (productApplication.getUpdateTime() != null) {
            params[index] = productApplication.getUpdateTime();
        }

        int update = CRUDUtils.update(sqlBuilder.toString(), params);
        logger.debug("update:" + update);
    }

    @Override
    public List<ProductApplication> selectAllProductApplication() throws Exception {
        String sql = "select * from tb_product_application";
        List<ProductApplication> productApplications = CRUDUtils.queryMore(sql, ProductApplication.class, null);
        logger.debug(productApplications.toString());
        return productApplications;
    }

    @Override
    public ProductApplication selectApplicationById(Integer id) throws Exception {
        String sql = "select * from tb_product_application where id =?";
        ProductApplication productApplication = CRUDUtils.query(sql, ProductApplication.class, id);
        logger.debug(String.valueOf(productApplication));
        return productApplication;
    }

    @Override
    public void updateStatusById(ProductApplication productApplication) throws Exception {
        String sql = "update tb_product_application set `status` = ? where id = ?";
        Object[] params = {productApplication.getStatus(),productApplication.getId()};
        params[params.length - 1] = productApplication.getId();
        int update = CRUDUtils.update(sql, params);
        logger.debug("update:" + update);

        if (update == 0) {
            throw new MyException(ResultCode.UPDATE_FAILED);
        }
    }


}
