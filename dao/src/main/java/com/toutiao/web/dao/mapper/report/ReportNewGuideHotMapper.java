package com.toutiao.web.dao.mapper.report;

import com.toutiao.app.dao.report.ReportNewGuideHot;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportNewGuideHotMapper {
    /**
     * 根据城市ID查询全部数据
     *
     * @param cityId
     * @return
     */
    List<ReportNewGuideHot> selectAll(@Param("cityId") Integer cityId);
}