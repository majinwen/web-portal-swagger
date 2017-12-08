package com.toutiao.web.service.repository.admin;

import com.toutiao.web.dao.entity.admin.VillageEntity;

import java.net.UnknownHostException;
import java.util.List;

public interface SysVillageService {
    List GetNearByhHouseAndDistance(String index, String type, double lat, double lon, String distance) throws Exception;
    VillageEntity findVillageById(String index, String type,Integer id) throws UnknownHostException;

}
