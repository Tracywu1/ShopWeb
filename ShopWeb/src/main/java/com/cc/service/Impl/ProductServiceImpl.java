package com.cc.service.Impl;

import com.cc.dao.Impl.ProductDaoImpl;
import com.cc.dao.ProductDao;
import com.cc.po.Product;
import com.cc.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = new ProductDaoImpl();
    @Override
    public List<Product> getAll() {
        return productDao.selectAll();
    }

    public void add(Product product){
        productDao.insert(product);
    }

}
