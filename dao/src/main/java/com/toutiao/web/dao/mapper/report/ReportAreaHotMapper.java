package com.toutiao.web.dao.mapper.report;

import com.toutiao.app.dao.report.ReportAreaHot;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportAreaHotMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportAreaHot record);

    int insertSelective(ReportAreaHot record);

    ReportAreaHot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReportAreaHot record);

    int updateByPrimaryKey(ReportAreaHot record);

    /**
     * 根据城市ID查询全部数据
     *
     * @param cityId
     * @return
     */
    List<ReportAreaHot> selectAll(@Param("cityId") Integer cityId);
}