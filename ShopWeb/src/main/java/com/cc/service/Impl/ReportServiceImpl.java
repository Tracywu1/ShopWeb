package com.cc.service.Impl;

import com.cc.common.Constants;
import com.cc.dao.Impl.ReportDaoImpl;
import com.cc.dao.Impl.StoreApplicationDaoImpl;
import com.cc.dao.Impl.UserDaoImpl;
import com.cc.dao.ReportDao;
import com.cc.dao.StoreApplicationDao;
import com.cc.dao.UserDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.po.Report;
import com.cc.po.StoreApplication;
import com.cc.service.ReportService;
import com.cc.service.StoreApplicationService;

import java.util.List;

public class ReportServiceImpl implements ReportService {
    private final ReportDao reportDao = new ReportDaoImpl();
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public void add(Report report,Integer userId) throws Exception {
        String username = userDao.selectById(userId).getUsername();
        report.setUsername(username);
        reportDao.insertSelective(report);
    }

    @Override
    public void accept(Integer id) throws Exception {
        Report report = reportDao.selectById(id);
        if (report.getStatus() == Constants.ApplyStatus.TO_BE_PROCESSED.getNum()) {
            report.setStatus(Constants.ApplyStatus.PROCESSED.getNum());
            reportDao.updateByIdSelective(report);
        } else {
            throw new MyException(ResultCode.WRONG_ORDER_STATUS);
        }
    }

    @Override
    public void refuse(Integer id) throws Exception {
        Report report = reportDao.selectById(id);
        if (report.getStatus() == Constants.ApplyStatus.TO_BE_PROCESSED.getNum()) {
            report.setStatus(Constants.ApplyStatus.REFUSED.getNum());
            reportDao.updateByIdSelective(report);
        } else {
            throw new MyException(ResultCode.WRONG_ORDER_STATUS);
        }
    }

    @Override
    public Report getById(Integer id) throws Exception {
       return reportDao.selectById(id);
    }

    @Override
    public List<Report> getAll() throws Exception {
        return reportDao.selectAllReport();
    }
}
