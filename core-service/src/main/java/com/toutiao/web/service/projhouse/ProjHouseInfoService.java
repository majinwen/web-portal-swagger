package com.toutiao.web.service.projhouse;

import com.toutiao.web.dao.entity.admin.ProjHouseInfo;
import com.toutiao.web.domain.query.ProjHouseInfoQuery;

import java.util.List;
import java.util.Map;

public interface ProjHouseInfoService {

       Map<String, Object> queryProjHouseByhouseIdandLocation(double lat, double lon);

       List queryProjHouseInfo(ProjHouseInfoQuery ProjHouseInfoQuery);

       Map<String,Object> queryByHouseId(Integer houseId);

       List  queryBySearchBox(String text);

        boolean saveProjHouseInfo(ProjHouseInfo projHouseInfo);

}
