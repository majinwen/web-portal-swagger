package com.toutiao.app.service.sellhouse;


import com.toutiao.app.domain.sellhouse.*;


public interface SellHouseService {

    /**
     * 二手房房源详情
     * @param houseId
     * @return
     */
    SellHouseDetailsDo getSellHouseByHouseId(String houseId);

    /**
     * 二手房房源详情消息推送
     * @param houseId
     * @return
     */
    SellHouseDo querySellHouseByHouseId(String houseId);

    /**
     * 认领二手房房源经纪人
     * @param houseId
     * @return
     */
    AgentsBySellHouseDo getAgentByHouseId(Integer houseId);

    /**
     * 条件查询二手房房源
     * @param sellHouseQueryDo
     * @return
     */
    SellHouseDomain getSellHouseByChoose(SellHouseDoQuery sellHouseQueryDo);

    /**
     * 查询二手房推荐房源
     * @param sellHouseQueryDo
     * @return
     */
    SellHouseDomain getRecommendSellHouse(SellHouseDoQuery sellHouseQueryDo);


    /**
     * 二手房搜索结果列表页
     * @param sellHouseDoQuery
     * @return
     */
    SellHouseSearchDomain getSellHouseList(SellHouseDoQuery sellHouseDoQuery);



    /**
     *
     * @param sellHouseBeSureToSnatchDoQuery
     * @return
     */
    SellHouseBeSureToSnatchDomain getBeSureToSnatchList(SellHouseBeSureToSnatchDoQuery sellHouseBeSureToSnatchDoQuery);

    /**
     * 推荐房源
     *
     * @param recommendEsf5DoQuery
     * @return
     */
    SellHouseSearchDomain getRecommendEsf5(RecommendEsf5DoQuery recommendEsf5DoQuery);
}
