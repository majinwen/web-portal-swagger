package com.toutiao.app.service.sellhouse;

import com.toutiao.app.domain.sellhouse.CutPriceShellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.CutPriceShellHouseDomain;

public interface CutPriceSellHouseRestService {
    /**
     * 获取降价房List
     */
    CutPriceShellHouseDomain getCutPriceHouse(CutPriceShellHouseDoQuery cutPriceHouseRequest);
}
