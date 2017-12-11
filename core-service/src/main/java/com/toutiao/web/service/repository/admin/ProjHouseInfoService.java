package com.toutiao.web.service.repository.admin;

import com.toutiao.web.dao.entity.admin.ProjHouseInfo;
import com.toutiao.web.domain.query.ProjHouseInfoQuery;

import java.util.List;
import java.util.Map;

public interface ProjHouseInfoService {

       List<ProjHouseInfo> queryProjHouseInfoByRang(String index, String type, String term, int first, int last);
       Map<String,Object> queryProjHouseInfo(ProjHouseInfoQuery ProjHouseInfoQuery);
}
