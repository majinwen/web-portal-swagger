package com.toutiao.web.service.projhouse;

import com.toutiao.web.domain.query.ProjHouseInfoQuery;

import java.util.Map;

public interface ProjHouseInfoService {

       Map<String, Object> queryProjHouseByhouseIdandLocation(double lat, double lon);

       Map<String,Object> queryProjHouseInfo(ProjHouseInfoQuery ProjHouseInfoQuery);

       Map<String,Object> queryByHouseId(Integer houseId);

}
