package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.CpcSellHouse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CpcSellHouseMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CpcSellHouse record);

    int insertSelective(CpcSellHouse record);

    CpcSellHouse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CpcSellHouse record);

    int updateByPrimaryKey(CpcSellHouse record);

    List<CpcSellHouse> selectByLastDate(@Param("lastData")String lastData);


    List<CpcSellHouse> selectByLastDateRepair(@Param("lastData")String lastData);


    List<CpcSellHouse> selectByBeforeYesterday(@Param("beforeYesterday")String beforeYesterday);


    List<CpcSellHouse> selectByBeforeYesterdayRepair(@Param("beforeYesterday")String beforeYesterday);
}