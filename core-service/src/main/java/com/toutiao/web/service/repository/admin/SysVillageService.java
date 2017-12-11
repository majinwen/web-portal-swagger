package com.toutiao.web.service.repository.admin;

import com.toutiao.web.dao.entity.admin.VillageEntity;
import com.toutiao.web.domain.app.VillageRequest;

import java.net.UnknownHostException;
import java.util.List;

public interface SysVillageService {
    List GetNearByhHouseAndDistance(String index, String type, double lat, double lon, String distance) throws Exception;

    List findVillageByConditions(String index, String type, VillageRequest villageRequest);
}
