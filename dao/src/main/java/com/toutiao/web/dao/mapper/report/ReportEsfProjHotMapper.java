package com.toutiao.web.dao.mapper.report;

import com.toutiao.app.dao.report.ReportEsfProjHot;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportEsfProjHotMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportEsfProjHot record);

    int insertSelective(ReportEsfProjHot record);

    ReportEsfProjHot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReportEsfProjHot record);

    int updateByPrimaryKey(ReportEsfProjHot record);

    /**
     * 根据城市ID查询全部数据
     *
     * @param cityId
     * @return
     */
    List<ReportEsfProjHot> selectAll(@Param("cityId") Integer cityId);
}