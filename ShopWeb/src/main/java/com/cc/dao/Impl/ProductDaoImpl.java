package com.cc.dao.Impl;

import com.cc.dao.ProductDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.po.Product;
import com.cc.utils.CRUDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);

    @Override
    public void insertSelective(Product product) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("insert into tb_product");
        StringBuilder columnsBuilder = new StringBuilder("(");
        StringBuilder valuesBuilder = new StringBuilder("(");
        if (product.getId() != null) {
            columnsBuilder.append("`id`,");
            valuesBuilder.append("?,");
        }
        if (product.getStoreId() != null) {
            columnsBuilder.append("`storeId`,");
            valuesBuilder.append("?,");
        }
        if (product.getProductName() != null) {
            columnsBuilder.append("`productName`,");
            valuesBuilder.append("?,");
        }
        if (product.getStoreName() != null) {
            columnsBuilder.append("`storeName`,");
            valuesBuilder.append("?,");
        }
        if (product.getDescription() != null) {
            columnsBuilder.append("`description`,");
            valuesBuilder.append("?,");
        }
        if (product.getImage() != null) {
            columnsBuilder.append("`image`,");
            valuesBuilder.append("?,");
        }
        if (product.getPrice() != null) {
            columnsBuilder.append("`price`,");
            valuesBuilder.append("?,");
        }
        if (product.getProductCount() != null) {
            columnsBuilder.append("`productCount`,");
            valuesBuilder.append("?,");
        }
        if (product.getCreateTime() != null) {
            columnsBuilder.append("`createTime`,");
            valuesBuilder.append("?,");
        }
        if (product.getUpdateTime() != null) {
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

        if (product.getId() != null) {
            count++;
        }
        if (product.getStoreId() != null) {
            count++;
        }
        if (product.getProductName() != null && !product.getProductName().isEmpty()) {
            count++;
        }
        if (product.getStoreName() != null && !product.getStoreName().isEmpty()) {
            count++;
        }
        if (product.getDescription() != null && !product.getDescription().isEmpty()) {
            count++;
        }
        if (product.getImage() != null && !product.getImage().isEmpty()) {
            count++;
        }
        if (product.getPrice() != null) {
            count++;
        }
        if (product.getProductCount() != null) {
            count++;
        }
        if (product.getCreateTime() != null) {
            count++;
        }
        if (product.getUpdateTime() != null) {
            count++;
        }

        Object[] params = new Object[count];

        int index = 0;

        if (product.getId() != null) {
            params[index] = product.getId();
            index++;
        }
        if (product.getStoreId() != null) {
            params[index] = product.getStoreId();
            index++;
        }
        if (product.getProductName() != null && !product.getProductName().isEmpty()) {
            params[index] = product.getProductName();
            index++;
        }
        if (product.getStoreName() != null && !product.getStoreName().isEmpty()) {
            params[index] = product.getStoreName();
            index++;
        }
        if (product.getDescription() != null && !product.getDescription().isEmpty()) {
            params[index] = product.getDescription();
            index++;
        }
        if (product.getImage() != null && !product.getImage().isEmpty()) {
            params[index] = product.getImage();
            index++;
        }
        if (product.getPrice() != null) {
            params[index] = product.getPrice();
            index++;
        }
        if (product.getProductCount() != null) {
            params[index] = product.getProductCount();
            index++;
        }
        if (product.getCreateTime() != null) {
            params[index] = product.getCreateTime();
            index++;
        }
        if (product.getUpdateTime() != null) {
            params[index] = product.getUpdateTime();
        }

        int update = CRUDUtils.update(sqlBuilder.toString(), params);
        logger.debug("update:" + update);
    }

    @Override
    public void delete(Integer id) throws Exception {
        String sql = "delete from tb_product where id = ?";
        int update = CRUDUtils.update(sql, id);
        logger.debug("update:" + update);
    }

    @Override
    public void deleteByIds(int[] ids) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("delete from tb_product where id in (");

        for (int i = 0; i < ids.length; i++) {
            if (i == ids.length - 1) {
                sqlBuilder.append("? ");
            } else {
                sqlBuilder.append("?, ");
            }
        }

        sqlBuilder.append(")");
        String sql = sqlBuilder.toString();
        logger.debug(sql);

        // 将int[]转换为Object[]
        Object[] params = Arrays.stream(ids).boxed().toArray();

        int update = CRUDUtils.update(sql, params);
        logger.debug("update:" + update);
        if (update == 0) {
            throw new MyException(ResultCode.DELETE_FAILED);
        }
    }

    @Override
    public List<Product> selectAllProduct() throws Exception {
        String sql = "select * from tb_product";
        List<Product> products = CRUDUtils.queryMore(sql, Product.class, null);
        logger.debug(products.toString());
        return products;
    }

    @Override
    public Product selectProductById(Integer id) throws Exception {
        String sql = "select * from tb_product where id =?";
        Product product = CRUDUtils.query(sql, Product.class, id);
        logger.debug(String.valueOf(product));
        return product;
    }

    @Override
    public Product selectByProductName(String productName) throws Exception {
        String sql = "select * from tb_product where productName =?";
        Product product = CRUDUtils.query(sql, Product.class, productName);
        logger.debug(String.valueOf(product));
        return product;
    }

    @Override
    public List<Product> selectByProductNameList(String productName) throws Exception {
        String sql = "select * from tb_product where productName LIKE ?";
        List<Product> products = CRUDUtils.queryMore(sql, Product.class, productName);
        logger.debug(String.valueOf(products));
        return products;
    }

    @Override
    public void updateByIdSelective(Product product) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("update tb_product");
        sqlBuilder.append(" ");
        sqlBuilder.append("set");

        if (product.getProductName() != null) {
            sqlBuilder.append("`productName` = ?,");
        }
        if (product.getDescription() != null) {
            sqlBuilder.append("`description` = ?,");
        }
        if (product.getImage() != null) {
            sqlBuilder.append("`image` = ?,");
        }
        if (product.getPrice() != null) {
            sqlBuilder.append("`price` = ?,");
        }
        if (product.getProductCount() != null) {
            sqlBuilder.append("`productCount` = ?,");
        }

        // 删除最后一个逗号
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(" ");
        sqlBuilder.append("where id = ?");

        int count = 0;

        if (product.getProductName() != null && !product.getProductName().isEmpty()) {
            count++;
        }
        if (product.getDescription() != null && !product.getDescription().isEmpty()) {
            count++;
        }
        if (product.getImage() != null && !product.getImage().isEmpty()) {
            count++;
        }
        if (product.getPrice() != null) {
            count++;
        }
        if (product.getProductCount() != null) {
            count++;
        }

        Object[] params = new Object[count + 1];

        int index = 0;

        if (product.getProductName() != null && !product.getProductName().isEmpty()) {
            params[index] = product.getProductName();
            index++;
        }
        if (product.getDescription() != null && !product.getDescription().isEmpty()) {
            params[index] = product.getDescription();
            index++;
        }
        if (product.getImage() != null && !product.getImage().isEmpty()) {
            params[index] = product.getImage();
            index++;
        }
        if (product.getPrice() != null) {
            params[index] = product.getPrice();
            index++;
        }
        if (product.getProductCount() != null) {
            params[index] = product.getProductCount();
        }

        params[params.length - 1] = product.getId();

        int update = CRUDUtils.update(sqlBuilder.toString(), params);
        logger.debug("update:" + update);

        if (update == 0) {
            throw new MyException(ResultCode.UPDATE_FAILED);
        }
    }

    @Override
    public Integer selectMonthlySalesCount(Integer id) throws Exception {
        String sql ="SELECT COUNT(1) FROM tb_order_item oi JOIN tb_order o ON oi.orderNo = o.orderNo WHERE oi.createTime >= DATE_ADD(CURDATE(), INTERVAL -DAY(CURDATE()) + 1 DAY) AND oi.createTime < DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 1 MONTH), INTERVAL -DAY(DATE_ADD(CURDATE(), INTERVAL 1 MONTH)) DAY) AND oi.productId = ? AND o.status = 3";
        int count = CRUDUtils.queryCount(sql,id);
        logger.debug("count:"+count);
        return count;
    }

    @Override
    public List<Product> selectByPage(int begin, int size) throws Exception {
        Object[] params = {begin, size};
        String sql = "select * from tb_product limit ?, ?";
        List<Product> products = CRUDUtils.queryMore(sql, Product.class, params);
        logger.debug(products.toString());
        return products;
    }

    @Override
    public int selectTotalCount() throws Exception {
        String sql = "select count(*) from tb_product";
        int totalCount = CRUDUtils.queryCount(sql, null);
        logger.debug("totalCount:" + totalCount);
        return totalCount;
    }

    @Override
    public List<Product> selectByPageAndCondition(int begin, int size, String productName, String storeName) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("select * from tb_product where 1=1");

        fuzzyQuery(productName, storeName, sqlBuilder);

        sqlBuilder.append(" limit ?, ?");
        String sql = sqlBuilder.toString();

        logger.debug(sql);

        int count = 0;
        if (productName != null && !productName.isEmpty()) {
            count++;
        }
        if (storeName != null && !storeName.isEmpty()) {
            count++;
        }

        Object[] params = new Object[count + 2];
        fuzzyQuery2(productName, storeName, params);

        params[params.length - 2] = begin;
        params[params.length - 1] = size;

        List<Product> products = CRUDUtils.queryMore(sql, Product.class, params);
        logger.debug(products.toString());
        return products;
    }

    @Override
    public int selectTotalCountByCondition(String productName, String storeName) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("select count(*) from tb_product where 1=1 ");

        fuzzyQuery(productName, storeName, sqlBuilder);
        String sql = sqlBuilder.toString();

        logger.debug(sql);

        int count = 0;
        if (productName != null && !productName.isEmpty()) {
            count++;
        }
        if (storeName != null && !storeName.isEmpty()) {
            count++;
        }

        Object[] params = new Object[count];
        fuzzyQuery2(productName, storeName, params);

        int totalCount = CRUDUtils.queryCount(sql, params);
        logger.debug("totalCount:" + totalCount);
        return totalCount;
    }

    private void fuzzyQuery2(String productName, String storeName, Object[] params) {
        int index = 0;

        if (productName != null && !productName.isEmpty()) {
            params[index] = "%" + productName + "%";
            index++;
        }

        if (storeName != null && !storeName.isEmpty()) {
            params[index] = "%" + storeName + "%";
        }
    }

    private void fuzzyQuery(String productName, String storeName, StringBuilder sqlBuilder) {
        if (productName != null && !productName.isEmpty()) {
            sqlBuilder.append(" and productName like ?");
        }

        if (storeName != null && !storeName.isEmpty()) {
            sqlBuilder.append(" and storeName like ?");
        }
    }

}