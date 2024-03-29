package com.toutiao.web.dao.mapper.report;

import com.toutiao.app.dao.report.ReportPipelineRecordEveryMonth;
import com.toutiao.web.dao.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportPipelineRecordEveryMonthMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportPipelineRecordEveryMonth record);

    int insertSelective(ReportPipelineRecordEveryMonth record);

    ReportPipelineRecordEveryMonth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReportPipelineRecordEveryMonth record);

    int updateByPrimaryKey(ReportPipelineRecordEveryMonth record);

    List<ReportPipelineRecordEveryMonth> selectFoldLineDistrictInfo(@Param("code") String code,@Param("type") Integer type);

    ReportPipelineRecordEveryMonth selectPlotRindRatio(@Param("code") String code,@Param("type") Integer type);
}