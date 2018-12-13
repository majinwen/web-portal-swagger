package com.toutiao.web.dao.mapper.foldline;

import com.toutiao.app.domain.foldline.reportPipelineRecordEveryMonth;
import com.toutiao.web.dao.BaseDao;

public interface reportPipelineRecordEveryMonthMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(reportPipelineRecordEveryMonth record);

    int insertSelective(reportPipelineRecordEveryMonth record);

    reportPipelineRecordEveryMonth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(reportPipelineRecordEveryMonth record);

    int updateByPrimaryKey(reportPipelineRecordEveryMonth record);
}