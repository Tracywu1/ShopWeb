package com.cc.dao;

import com.cc.po.Report;
import com.cc.po.StoreApplication;

import java.util.List;

public interface ReportDao {
    /**
     * 选择性地插入举报
     * @param report
     * @throws Exception
     */
    void insertSelective(Report report)throws Exception;

    /**
     * 查询所有举报
     * @return
     * @throws Exception
     */
    List<Report> selectAllReport()throws  Exception;

    /**
     * 根据id查询举报信息
     * @param id
     * @return
     * @throws Exception
     */
    Report selectById(Integer id)throws Exception;

    /**
     * 选择性地修改举报信息
     * @param report
     * @throws Exception
     */
    void updateByIdSelective(Report report) throws Exception;

}
