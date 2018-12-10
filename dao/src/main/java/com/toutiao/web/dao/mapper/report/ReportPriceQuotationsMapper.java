package com.toutiao.web.dao.mapper.report;

import com.toutiao.app.dao.report.ReportPriceQuotations;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportPriceQuotationsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportPriceQuotations record);

    int insertSelective(ReportPriceQuotations record);

    ReportPriceQuotations selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReportPriceQuotations record);

    int updateByPrimaryKey(ReportPriceQuotations record);
}