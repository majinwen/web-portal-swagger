package com.toutiao.web.service.repository.admin;

import java.util.List;

public interface SysVillage {
    List GetNearByhHouseAndDistance(String index, String type, double lat, double lon, String distance) throws Exception;
}
