package com.cc.service;

import com.cc.po.Product;

import java.util.List;

/**
 * @author 32119
 */
public interface ProductService {
    /**
     * 查询所有
     * @return
     */
    List<Product> getAll();
}
