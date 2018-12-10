package com.toutiao.web.dao.mapper.report;

import com.toutiao.app.dao.report.ReportRentPriceDistrbution;

public interface ReportRentPriceDistrbutionMapper {
    int deleteByPrimaryKey(Short id);

    int insert(ReportRentPriceDistrbution record);

    int insertSelective(ReportRentPriceDistrbution record);

    ReportRentPriceDistrbution selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(ReportRentPriceDistrbution record);

    int updateByPrimaryKey(ReportRentPriceDistrbution record);
}