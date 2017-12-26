package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.lotRatio;
import com.toutiao.web.dao.entity.officeweb.lotRatioExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


public interface lotRatioMapper extends BaseDao {
    int countByExample(lotRatioExample example);

    int deleteByExample(lotRatioExample example);

    int deleteByPrimaryKey(Integer buildingId);

    int insert(lotRatio record);

    int insertSelective(lotRatio record);

    List<lotRatio> selectByExample(lotRatioExample example);

    lotRatio selectByPrimaryKey(Integer buildingId);

    int updateByExampleSelective(@Param("record") lotRatio record, @Param("example") lotRatioExample example);

    int updateByExample(@Param("record") lotRatio record, @Param("example") lotRatioExample example);

    int updateByPrimaryKeySelective(lotRatio record);

    int updateByPrimaryKey(lotRatio record);
}