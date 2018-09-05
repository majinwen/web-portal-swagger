package com.toutiao.web.service.plot;

import com.toutiao.web.dao.entity.admin.ProjHouseInfoES;
import com.toutiao.web.dao.entity.admin.VillageEntityES;
import com.toutiao.web.domain.query.VillageRequest;

import java.util.List;
import java.util.Map;

public interface PlotService {
    List GetNearByhHouseAndDistance(double lat, double lon);

    List findVillageByConditions(VillageRequest villageRequest);

    List findNearByVillageByConditions(VillageRequest villageRequest);

    void saveParent(VillageEntityES village);

    void saveChild(ProjHouseInfoES projHouseInfoes);

    Map queryPlotByRentId(String rentPlotId);

    /**
     * 根据小区id查询小区信息
     * @param PlotId
     * @return
     */
    Map queryPlotByPlotId(String PlotId);
}
