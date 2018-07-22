package com.toutiao.app.service.sellhouse;

import com.toutiao.app.domain.sellhouse.MustBuyShellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.MustBuyShellHouseDomain;

public interface MustBuySellHouseRestService {
    /**
     * 获取不卖亏二手房List
     */
    MustBuyShellHouseDomain getMustBuySellHouse(MustBuyShellHouseDoQuery cutPriceHouseRequest, Integer topicType);
}
