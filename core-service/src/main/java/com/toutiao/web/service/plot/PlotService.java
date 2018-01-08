package com.toutiao.web.service.plot;

import com.toutiao.web.dao.entity.admin.ProjHouseInfoES;
import com.toutiao.web.dao.entity.admin.VillageEntityES;
import com.toutiao.web.domain.query.VillageRequest;

import java.util.List;

public interface PlotService {
    List GetNearByhHouseAndDistance(double lat, double lon);

    List findVillageByConditions(VillageRequest villageRequest);

    List findNearByVillageByConditions(VillageRequest villageRequest);

    void saveParent(VillageEntityES village);

    void saveChild(ProjHouseInfoES projHouseInfoes);
}
