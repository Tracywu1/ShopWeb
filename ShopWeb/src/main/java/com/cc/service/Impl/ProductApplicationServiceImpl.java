package com.cc.service.Impl;

import com.cc.common.Constants;
import com.cc.dao.Impl.ProductApplicationDaoImpl;
import com.cc.dao.ProductApplicationDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.po.ProductApplication;
import com.cc.service.ProductApplicationService;

import java.util.List;

public class ProductApplicationServiceImpl implements ProductApplicationService {
    private final ProductApplicationDao productApplicationDao = new ProductApplicationDaoImpl();

    @Override
    public void addApplication(ProductApplication productApplication) throws Exception {
        productApplicationDao.insertApplicationSelective(productApplication);
    }

    @Override
    public void accept(Integer id) throws Exception {
        ProductApplication productApplication = productApplicationDao.selectApplicationById(id);
        if (productApplication.getStatus() == Constants.ApplyStatus.TO_BE_PROCESSED.getNum()) {
            productApplication.setStatus(Constants.ApplyStatus.PROCESSED.getNum());
            productApplicationDao.updateStatusById(productApplication);
        } else {
            throw new MyException(ResultCode.WRONG_ORDER_STATUS);
        }
    }

    @Override
    public void refuse(Integer id) throws Exception {
        ProductApplication productApplication = productApplicationDao.selectApplicationById(id);
        if (productApplication.getStatus() == Constants.ApplyStatus.TO_BE_PROCESSED.getNum()) {
            productApplication.setStatus(Constants.ApplyStatus.REFUSED.getNum());
            productApplicationDao.updateStatusById(productApplication);
        } else {
            throw new MyException(ResultCode.WRONG_ORDER_STATUS);
        }
    }

    @Override
    public ProductApplication getById(Integer id) throws Exception {
       return productApplicationDao.selectApplicationById(id);
    }

    @Override
    public List<ProductApplication> getAll() throws Exception {
        return productApplicationDao.selectAllProductApplication();
    }
}
