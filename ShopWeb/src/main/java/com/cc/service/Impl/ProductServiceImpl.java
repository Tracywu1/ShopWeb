package com.cc.service.Impl;

import com.cc.dao.Impl.ProductDaoImpl;
import com.cc.dao.ProductDao;
import com.cc.po.PageBean;
import com.cc.po.Product;
import com.cc.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = new ProductDaoImpl();
    @Override
    public List<Product> getAll() throws Exception {
        return productDao.selectAll();
    }

    public void add(Product product) throws Exception {
        productDao.insert(product);
    }

    @Override
    public void deleteInBatches(int[] ids) throws Exception {
        productDao.deleteByIds(ids);
    }

    @Override
    public PageBean<Product> selectByPage(int currentPage, int pageSize) throws Exception {
        // 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        int size = pageSize;

        // 查询当前页数据
        List<Product> rows = productDao.selectByPage(begin, size);

        //查询总记录数
        int totalCount = productDao.selectTotalCount();

        //封装PageBean对象
        PageBean<Product> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        return pageBean;
    }

    @Override
    public PageBean<Product> selectByPageAndCondition(int currentPage, int pageSize, Product product) throws Exception {
        // 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        int size = pageSize;

        // 处理brand条件，模糊表达式
        String productName = product.getProductName();
        if (productName != null && productName.length() > 0) {
            product.setProductName("%" + productName + "%");
        }

        String storeName = product.getStoreName();
        if (storeName != null && storeName.length() > 0) {
            product.setStoreName("%" + storeName + "%");
        }

        // 查询当前页数据
        List<Product> rows = productDao.selectByPageAndCondition(begin, size, product);

        // 查询总记录数
        int totalCount = productDao.selectTotalCountByCondition(product);

        // 封装PageBean对象
        PageBean<Product> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        return pageBean;
    }

}
