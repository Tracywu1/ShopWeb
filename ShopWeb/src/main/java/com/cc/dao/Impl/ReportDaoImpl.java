package com.cc.dao.Impl;

import com.cc.common.Constants;
import com.cc.dao.ReportDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.filter.LoginCheckFilter;
import com.cc.po.Report;
import com.cc.utils.CRUDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReportDaoImpl implements ReportDao {
    private static final Logger logger = LoggerFactory.getLogger(ReportDao.class);

    @Override
    public void insertSelective(Report report) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("insert into tb_report");
        StringBuilder columnsBuilder = new StringBuilder("(");
        StringBuilder valuesBuilder = new StringBuilder("(");
        if (report.getId() != null) {
            columnsBuilder.append("`id`,");
            valuesBuilder.append("?,");
        }
        if (report.getUserId() != null) {
            columnsBuilder.append("`storeId`,");
            valuesBuilder.append("?,");
        }
        if(report.getUsername()==null){
            columnsBuilder.append("`username`,");
            valuesBuilder.append("?,");
        }
        if (report.getContent() != null) {
            columnsBuilder.append("`content`,");
            valuesBuilder.append("?,");
        }
        if (report.getReportTime() != null) {
            columnsBuilder.append("`reportTime`,");
            valuesBuilder.append("?,");
        }
        if (report.getStatus() == null) {
            columnsBuilder.append("`status`,");
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

        if (report.getId() != null) {
            count++;
        }
        if (report.getUserId() != null) {
            count++;
        }
        if(report.getUsername() ==null && report.getUsername().isEmpty()){
            count++;
        }
        if (report.getContent() != null && !report.getContent().isEmpty()) {
            count++;
        }
        if (report.getReportTime() != null ) {
            count++;
        }
        if (report.getStatus() == null) {
            count++;
        }

        Object[] params = new Object[count];

        int index = 0;

        if (report.getId() != null) {
            params[index] = report.getId();
            index++;
        }
        if (report.getUserId() != null) {
            params[index] = report.getUserId();
            index++;
        }
        if(report.getUsername()==null){
            params[index] = LoginCheckFilter.currentUser.getUsername();
        }
        if (report.getContent() != null && !report.getContent().isEmpty()) {
            params[index] = report.getContent();
            index++;
        }
        if (report.getReportTime() != null) {
            params[index] = report.getReportTime();
            index++;
        }

        //设置申请状态
        params[index] = Constants.ApplyStatus.TO_BE_PROCESSED.getNum();

        int update = CRUDUtils.update(sqlBuilder.toString(), params);
        logger.debug("update:" + update);
    }

    @Override
    public List<Report> selectAllReport() throws Exception {
        String sql = "select * from tb_report";
        List<Report> reports = CRUDUtils.queryMore(sql, Report.class, null);
        logger.debug(reports.toString());
        return reports;
    }

    @Override
    public Report selectById(Integer id) throws Exception {
        String sql = "select * from tb_report where id =?";
        Report report = CRUDUtils.query(sql, Report.class, id);
        logger.debug(String.valueOf(report));
        return report;
    }

    @Override
    public void updateByIdSelective(Report report) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("update tb_report");
        sqlBuilder.append(" ");
        sqlBuilder.append("set");

        if (report.getStatus() != null) {
            sqlBuilder.append("`status` = ?,");
        }

        // 删除最后一个逗号
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(" ");
        sqlBuilder.append("where id = ?");

        int count = 0;

        if (report.getStatus() != null) {
            count++;
        }

        Object[] params = new Object[count + 1];

        int index = 0;

        if (report.getStatus() != null) {
            params[index] = report.getStatus();
        }


        params[params.length - 1] = report.getId();

        int update = CRUDUtils.update(sqlBuilder.toString(), params);
        logger.debug("update:" + update);

        if (update == 0) {
            throw new MyException(ResultCode.UPDATE_FAILED);
        }
    }


}
