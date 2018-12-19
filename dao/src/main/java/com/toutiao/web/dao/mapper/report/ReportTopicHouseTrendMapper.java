package com.toutiao.web.dao.mapper.report;

import com.toutiao.app.dao.report.ReportTopicHouseTrend;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportTopicHouseTrendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportTopicHouseTrend record);

    int insertSelective(ReportTopicHouseTrend record);

    ReportTopicHouseTrend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReportTopicHouseTrend record);

    int updateByPrimaryKey(ReportTopicHouseTrend record);
}