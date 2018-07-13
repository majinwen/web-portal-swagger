package com.toutiao.app.service.sellhouse;

import com.toutiao.app.domain.sellhouse.LowerPriceShellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.LowerPriceShellHouseDomain;

public interface LowerPriceSellHouseRestService {
    /**
     * 获取捡漏房List
     */
    LowerPriceShellHouseDomain getLowerPriceHouse(LowerPriceShellHouseDoQuery lowerPriceShellHouseDoQuery);
}
