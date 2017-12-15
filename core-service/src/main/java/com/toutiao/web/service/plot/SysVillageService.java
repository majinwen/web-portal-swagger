package com.toutiao.web.service.plot;

import com.toutiao.web.domain.query.VillageRequest;

import java.util.List;

public interface SysVillageService {
    List GetNearByhHouseAndDistance(double lat, double lon);

    List findVillageByConditions(VillageRequest villageRequest);
}
