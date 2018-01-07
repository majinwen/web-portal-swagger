package com.toutiao.web.service.projhouse;

import com.toutiao.web.domain.query.ProjHouseInfoQuery;

import java.util.List;
import java.util.Map;

public interface ProjHouseInfoService {

    List queryProjHouseByhouseIdandLocation(String houseId, double lat, double lon);

    List queryProjHouseInfo(ProjHouseInfoQuery ProjHouseInfoQuery);

    List queryNearByProjHouseInfo(ProjHouseInfoQuery ProjHouseInfoQuery);

    Map<String, Object> queryByHouseId(Integer houseId);

    /*List queryBySearchBox(String text);*/

    /*void saveProjHouseInfo(ProjHouseInfoES projHouseInfoes);*/

}
