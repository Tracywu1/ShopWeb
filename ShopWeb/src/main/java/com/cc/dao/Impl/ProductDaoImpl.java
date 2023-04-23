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
    public void insert(Product product) throws Exception {
        Object[] params = {product.getStoreName(),product.getProductName(),product.getPrice(),product.getProductCount(),product.getDescription()};
        String sql = "insert into tb_product(storeName,productName,price,productCount,description) values(?,?,?,?,?)";
        int update = CRUDUtils.update(sql, params);
        logger.debug("update:" + update);
    }

    public void insertSelective(Product product) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("insert into tb_product");
        StringBuilder columnsBuilder = new StringBuilder("(");
        StringBuilder valuesBuilder = new StringBuilder("(");
        if (product.getId() != null) {
            columnsBuilder.append("`id`,");
            valuesBuilder.append("?,");
        }
        if (product.getProductName() != null) {
            columnsBuilder.append("`name`,");
            valuesBuilder.append("?,");
        }
        if (product.getImage() != null) {
            columnsBuilder.append("`image`,");
            valuesBuilder.append("?,");
        }
        if (product.getDescription() != null) {
            columnsBuilder.append("`description`,");
            valuesBuilder.append("?,");
        }
        if (product.getCreateId() != null) {
            columnsBuilder.append("`creatId`,");
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
        if (product.getSaleCount() != null) {
            columnsBuilder.append("`saleCount`,");
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

        int update = CRUDUtils.update(sqlBuilder.toString(), product);
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
        int update = 0;
        String sql = null;
        for (int id : ids) {
            sql = "delete from tb_product where id = ?";
            CRUDUtils.update(sql, id);
            update++;
        }
        logger.debug("update:" + update);
    }

    @Override
    public List<Product> selectAll() throws Exception {
        String sql = "select * from tb_product";
        List<Product> products = CRUDUtils.queryMore(sql,Product.class,null);
        logger.debug(products.toString());
        return products;
    }


    @Override
    public Product select(Integer id) throws Exception {
        String sql = "select * from tb_product where id =?";
        Product product = CRUDUtils.query(sql, Product.class, id);
        logger.debug(String.valueOf(product));
        return product;
    }

    @Override
    public List<Product> selectByProductName(String productName) throws Exception {
        String sql = "select * from tb_product where productName LIKE ?";
        List<Product> products = CRUDUtils.queryMore(sql, Product.class, productName);
        logger.debug(String.valueOf(products));
        return products;
    }

    @Override
    public void update(Product product) throws Exception {
        Object[] params = {product.getProductName(), product.getDescription(), product.getImage(), product.getPrice(), product.getId()};
        //待修改
        String sql = "update tb_product set productName = ?,description = ?,image = ?,price = ? where id = ?";
        int update = CRUDUtils.update(sql, params);
        logger.debug(String.valueOf(update));
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
    public List<Product> selectByPageAndCondition(int begin, int size, Product product) throws Exception {
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
            if (product.getStoreName() != null && !product.getStoreName().isEmpty()) {
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
        logger.debug(products.toString());
        return products;
    }

    @Override
    public int selectTotalCountByCondition(Product product) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("SELECT COUNT(*) FROM tb_product WHERE 1=1 ");

        fuzzyQuery(product, sqlBuilder);
        String sql = sqlBuilder.toString();

        Object[] params = new Object[0];
        if (product != null) {
            int count = 0;
            if (product.getProductName() != null && !product.getProductName().isEmpty()) {
                count++;
            }
            if (product.getStoreName() != null && !product.getStoreName().isEmpty()) {
                count++;
            }

            params = new Object[count];
            fuzzyQuery2(product, params);

        }

        int totalCount = CRUDUtils.queryCount(sql, params);
        logger.debug("totalCount:"+ totalCount);
        return totalCount;
    }

    private void fuzzyQuery2(Product product, Object[] params) {
        int index = 0;

        if (product.getProductName() != null && !product.getProductName().isEmpty()) {
            params[index] = "%" + product.getProductName() + "%";
            index++;
        }

        if (product.getStoreName() != null && !product.getStoreName().isEmpty()) {
            params[index] = "%" + product.getStoreName() + "%";
        }
    }

    private void fuzzyQuery(Product product, StringBuilder sqlBuilder) {
        if (product != null) {
            if (product.getProductName() != null && !product.getProductName().isEmpty()) {
                sqlBuilder.append(" AND productName LIKE ?");
            }

            if (product.getStoreName() != null && !product.getStoreName().isEmpty()) {
                sqlBuilder.append(" AND storeName LIKE ?");
            }
        }
    }

}
