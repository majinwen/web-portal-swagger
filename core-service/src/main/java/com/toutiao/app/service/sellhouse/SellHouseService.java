package com.toutiao.app.service.sellhouse;


import com.toutiao.app.domain.sellhouse.*;

import java.util.List;
import java.util.Map;


public interface SellHouseService {

    /**
     * 二手房房源详情
     * @param houseId
     * @return
     */
    SellAndClaimHouseDetailsDo getSellHouseByHouseId(String houseId);

    /**
     * 二手房附近好房列表
     * @param nearBySellHousesDo
     * @return
     */
    NearBySellHouseDomain getSellHouseByHouseIdAndLocation(NearBySellHousesDo nearBySellHousesDo);


    /**
     * 认领二手房房源经纪人
     * @param houseId
     * @return
     */
    AgentsBySellHouseDo getAgentByHouseId(Integer houseId);

    /**
     * 条件查询二手房房源
     * @param chooseSellHouseDo
     * @return
     */
//    ChooseSellHouseDomain getSellHouseByChoose(ChooseSellHouseDo chooseSellHouseDo);
}
