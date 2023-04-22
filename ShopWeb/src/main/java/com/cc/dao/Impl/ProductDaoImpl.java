package com.cc.dao.Impl;

import com.cc.dao.ProductDao;
import com.cc.po.Product;
import com.cc.utils.CRUDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);

    @Override
    public void insert(Product product) {
        Object[] params = {product.getProductName(), product.getDescription(), product.getImage(), product.getPrice()};
        String sql = "insert into tb_product(productName,description,image,price) values(?,?,?,?)";
        int update = CRUDUtils.update(sql, params);
        logger.info("update:" + update);
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from tb_product where id = ?";
        int update = CRUDUtils.update(sql, id);
        logger.info("update:" + update);
    }

    @Override
    public void deleteByIds(int[] ids) {
        int update = 0;
        String sql = null;
        for (int id : ids) {
            sql = "delete from tb_product where id = ?";
            CRUDUtils.update(sql, id);
            update++;
        }
        logger.info("update:" + update);
    }

    @Override
    public Product select(Integer id) {
        String sql = "select * from tb_product where id =?";
        Product product = CRUDUtils.query(sql, Product.class, id);
        logger.info(String.valueOf(product));
        return product;
    }

    @Override
    public List<Product> selectByProductName(String productName) {
        String sql = "select * from tb_product where productName LIKE ?";
        List<Product> products = CRUDUtils.queryMore(sql, Product.class, productName);
        logger.info(String.valueOf(products));
        return products;
    }

    @Override
    public void update(Product product) {
        Object[] params = {product.getProductName(), product.getDescription(), product.getImage(), product.getPrice(), product.getId()};
        //待修改
        String sql = "update tb_product set productName = ?,description = ?,image = ?,price = ? where id = ?";
        int update = CRUDUtils.update(sql, params);
        logger.info(String.valueOf(update));
    }

    @Override
    public List<Product> selectByPage(int begin, int size) {
        Object[] params = {begin, size};
        String sql = "select * from tb_product limit ?, ?";
        List<Product> products = CRUDUtils.queryMore(sql, Product.class, params);
        logger.info(products.toString());
        return products;
    }

    @Override
    public int selectTotalCount() {
        String sql = "select count(*) from tb_product";
        int totalCount = CRUDUtils.queryCount(sql, null);
        logger.info("totalCount:" + totalCount);
        return totalCount;
    }

    @Override
    public List<Product> selectByPageAndCondition(int begin, int size, Product product) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM tb_product WHERE 1=1");

        fuzzyQuery(product, sqlBuilder);

        sqlBuilder.append(" LIMIT ?, ?");
        String sql = sqlBuilder.toString();

        Object[] params;
        if (product != null) {
            int count = 0;
            if (product.getProductName() != null && !product.getProductName().isEmpty()) {
                count++;
            }
            if (product.getStore().getStoreName() != null && !product.getStore().getStoreName().isEmpty()) {
                count++;
            }

            params = new Object[count + 2];
            fuzzyQuery2(product, params);

        } else {
            params = new Object[2];
        }

        params[params.length - 2] = begin;
        params[params.length - 1] = size;

        List<Product> products = CRUDUtils.queryMore(sql, Product.class, params);
        logger.info(products.toString());
        return products;
    }

    @Override
    public int selectTotalCountByCondition(Product product) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT COUNT(*) FROM tb_product WHERE 1=1 ");

        fuzzyQuery(product, sqlBuilder);
        String sql = sqlBuilder.toString();

        Object[] params = new Object[0];
        if (product != null) {
            int count = 0;
            if (product.getProductName() != null && !product.getProductName().isEmpty()) {
                count++;
            }
            if (product.getStore().getStoreName() != null && !product.getStore().getStoreName().isEmpty()) {
                count++;
            }

            params = new Object[count];
            fuzzyQuery2(product, params);

        }

        int totalCount = CRUDUtils.queryCount(sql, params);
        logger.info("totalCount:"+ totalCount);
        return totalCount;
    }

    private void fuzzyQuery2(Product product, Object[] params) {
        int index = 0;

        if (product.getProductName() != null && !product.getProductName().isEmpty()) {
            params[index] = "%" + product.getProductName() + "%";
            index++;
        }

        if (product.getStore().getStoreName() != null && !product.getStore().getStoreName().isEmpty()) {
            params[index] = "%" + product.getStore().getStoreName() + "%";
        }
    }

    private void fuzzyQuery(Product product, StringBuilder sqlBuilder) {
        if (product != null) {
            if (product.getProductName() != null && !product.getProductName().isEmpty()) {
                sqlBuilder.append(" AND productName LIKE ?");
            }

            if (product.getStore().getStoreName() != null && !product.getStore().getStoreName().isEmpty()) {
                sqlBuilder.append(" AND storeName LIKE ?");
            }
        }
    }

}
