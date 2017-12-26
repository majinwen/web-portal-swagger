package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.PlotRatio;
import com.toutiao.web.dao.entity.officeweb.PlotRatioExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlotRatioMapper extends BaseDao {
    int countByExample(PlotRatioExample example);

    int deleteByExample(PlotRatioExample example);

    int deleteByPrimaryKey(Integer buildingId);

    int insert(PlotRatio record);

    int insertSelective(PlotRatio record);

    List<PlotRatio> selectByExample(PlotRatioExample example);

    PlotRatio selectByPrimaryKey(Integer buildingId);

    int updateByExampleSelective(@Param("record") PlotRatio record, @Param("example") PlotRatioExample example);

    int updateByExample(@Param("record") PlotRatio record, @Param("example") PlotRatioExample example);

    int updateByPrimaryKeySelective(PlotRatio record);

    int updateByPrimaryKey(PlotRatio record);
}