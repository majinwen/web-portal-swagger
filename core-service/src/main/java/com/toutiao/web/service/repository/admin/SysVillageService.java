package com.toutiao.web.service.repository.admin;

import com.toutiao.web.domain.query.VillageRequest;

import java.util.List;

public interface SysVillageService {
    List GetNearByhHouseAndDistance(String index, String type, double lat, double lon, Double distance) throws Exception;

    List findVillageByConditions(String index, String type, VillageRequest villageRequest) throws Exception;
}
