package com.toutiao.web.dao.mapper.report;

import com.toutiao.app.dao.report.ReportCity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportCityMapper {
    /**
     * 根据城市ID查询最新一条数据
     * @param cityId
     * @return
     */
    ReportCity selectOne(@Param("cityId")Integer cityId);
}