package com.toutiao.web.dao.mapper.activity;

import com.toutiao.app.domain.activity.ActivityStatistics;
import com.toutiao.web.dao.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityStatisticsMapper extends BaseDao {
    int insert(ActivityStatistics record);

    int insertSelective(ActivityStatistics record);

    ActivityStatistics selectActivityCount();

    /**
     * 更新次数
     * @param cumulativeNo
     * @param randomNo
     * @return
     */
    int updateActivityStatistics(@Param("cumulativeNo")Integer cumulativeNo, @Param("randomNo")Integer randomNo);
    /**
     * 更新总数
     * @param cumulativeNo
     * @param cumulativeNo
     * @return
     */
    int updateActivityGrandTotal(@Param("cumulativeNo")Integer cumulativeNo);
}