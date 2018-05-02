package com.toutiao.app.service.sellhouse;

import com.toutiao.app.domain.sellhouse.NearBySellHouseDomain;
import com.toutiao.app.domain.sellhouse.NearBySellHouseQueryDo;
import com.toutiao.app.domain.sellhouse.NearBySellHousesDo;

public interface NearSellHouseRestService {

    /**
     * 二手房附近好房列表
     * @param nearBySellHouseQueryDo
     * @return
     */
    NearBySellHouseDomain getSellHouseByHouseIdAndLocation(NearBySellHouseQueryDo nearBySellHouseQueryDo);
}
