package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.PriceTrend;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceTrendMapper extends BaseDao {

    List<PriceTrend> newhouseTrendList(@Param("buildingId")Integer buildingId, @Param("districtId")Integer districtId, @Param("areaId")Integer areaId);


}