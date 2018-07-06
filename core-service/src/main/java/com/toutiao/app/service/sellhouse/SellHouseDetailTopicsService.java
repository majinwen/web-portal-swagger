package com.toutiao.app.service.sellhouse;

import com.toutiao.app.domain.sellhouse.SellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.SellHouseDomain;

public interface SellHouseDetailTopicsService {


    /**
     * 获取小区专题房源详情
     * @param sellHouseQueryDo
     * @return
     */
    SellHouseDomain getNearbyTopicsSellHouse(SellHouseDoQuery sellHouseQueryDo);


    /**
     * 获取降价专题房源详情
     * @param sellHouseQueryDo
     * @return
     */
    SellHouseDomain getCutPriceTopicsSellHouse(SellHouseDoQuery sellHouseQueryDo);

    /**
     * 获取洼地专题房源详情
     * @param sellHouseQueryDo
     * @return
     */
    SellHouseDomain getLowPriceTopicsSellHouse(SellHouseDoQuery sellHouseQueryDo);

    /**
     * 获取逢出毕抢专题房源详情
     * @param sellHouseQueryDo
     * @return
     */
    SellHouseDomain getMustRobTopicsSellHouseDetail(SellHouseDoQuery sellHouseQueryDo);


    /**
     * 获取商圈户型专题房源详情
     * @param sellHouseQueryDo
     * @return
     */
    SellHouseDomain getAreaRoomTopicsSellHouseDetail(SellHouseDoQuery sellHouseQueryDo);




}
