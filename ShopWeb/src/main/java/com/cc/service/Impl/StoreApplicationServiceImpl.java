package com.cc.service.Impl;

import com.cc.common.Constants;
import com.cc.dao.Impl.StoreApplicationDaoImpl;
import com.cc.dao.StoreApplicationDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.po.StoreApplication;
import com.cc.service.StoreApplicationService;

import java.util.List;

public class StoreApplicationServiceImpl implements StoreApplicationService {
    private final StoreApplicationDao storeApplicationDao = new StoreApplicationDaoImpl();

    @Override
    public void addApplication(StoreApplication storeApplication) throws Exception {
        storeApplicationDao.insertApplicationSelective(storeApplication);
    }

    @Override
    public void accept(Integer id) throws Exception {
        StoreApplication storeApplication = storeApplicationDao.selectApplicationById(id);
        if (storeApplication.getStatus() == Constants.ApplyStatus.TO_BE_PROCESSED.getNum()) {
            storeApplication.setStatus(Constants.ApplyStatus.PROCESSED.getNum());
            storeApplicationDao.updateByIdSelective(storeApplication);
        } else {
            throw new MyException(ResultCode.WRONG_ORDER_STATUS);
        }
    }

    @Override
    public void refuse(Integer id) throws Exception {
        StoreApplication storeApplication = storeApplicationDao.selectApplicationById(id);
        if (storeApplication.getStatus() == Constants.ApplyStatus.TO_BE_PROCESSED.getNum()) {
            storeApplication.setStatus(Constants.ApplyStatus.REFUSED.getNum());
            storeApplicationDao.updateByIdSelective(storeApplication);
        } else {
            throw new MyException(ResultCode.WRONG_ORDER_STATUS);
        }
    }

    @Override
    public StoreApplication getById(Integer id) throws Exception {
       return storeApplicationDao.selectApplicationById(id);
    }

    @Override
    public List<StoreApplication> getAll() throws Exception {
        return storeApplicationDao.selectAllStoreApplication();
    }
}
