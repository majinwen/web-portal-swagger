package com.toutiao.app.service.sellhouse;

import com.toutiao.app.domain.sellhouse.SellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.SellHouseDomain;

public interface SellHouseDetailTopicsService {


    /**
     * 获取专题房源详情
     * @param sellHouseQueryDo
     * @return
     */
    SellHouseDomain getNearbyTopicsSellHouse(SellHouseDoQuery sellHouseQueryDo);
}
