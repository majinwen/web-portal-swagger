package com.toutiao.web.service.newhouse;

import com.toutiao.web.domain.query.NewHouseQuery;

import java.util.Map;

public interface NewHouseService {


    /**
     * 根绝条件筛选新房
     * @param newHouseQuery
     * @return
     */
    Map<String,Object> getNewHouse(NewHouseQuery newHouseQuery);
}
