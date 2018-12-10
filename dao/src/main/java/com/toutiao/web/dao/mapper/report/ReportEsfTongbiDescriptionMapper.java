package com.toutiao.web.dao.mapper.report;

import com.toutiao.app.dao.report.ReportEsfTongbiDescription;

public interface ReportEsfTongbiDescriptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportEsfTongbiDescription record);

    int insertSelective(ReportEsfTongbiDescription record);

    ReportEsfTongbiDescription selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReportEsfTongbiDescription record);

    int updateByPrimaryKey(ReportEsfTongbiDescription record);
}