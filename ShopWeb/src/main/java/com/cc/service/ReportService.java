package com.cc.service;

import com.cc.po.Report;
import com.cc.po.StoreApplication;

import java.util.List;

public interface ReportService {
    /**
     * 新增举报
     * @param report
     * @throws Exception
     */
    void add(Report report,Integer userId)throws Exception;

    /**
     * 认同举报
     * @param id
     * @throws Exception
     */
    void accept(Integer id)throws Exception;

    /**
     * 驳回
     * @param id
     * @throws Exception
     */
    void refuse(Integer id)throws Exception;

    /**
     * 根据id查询
     * @param id
     * @throws Exception
     */
    Report getById(Integer id)throws Exception;

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<Report> getAll()throws Exception;
}
