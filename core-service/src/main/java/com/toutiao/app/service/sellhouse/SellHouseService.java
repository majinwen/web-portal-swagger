package com.toutiao.app.service.sellhouse;


import com.toutiao.app.domain.sellhouse.*;


public interface SellHouseService {

    /**
     * 二手房房源详情
     * @param houseId
     * @return
     */
    SellHouseDetailsDo getSellHouseByHouseId(String houseId,String city);

    /**
     * 认领二手房房源经纪人
     * @param houseId
     * @return
     */
    AgentsBySellHouseDo getAgentByHouseId(Integer houseId);

    /**
     * 条件查询二手房房源
     * @param sellHouseQueryDo
     * @param city
     * @return
     */
    SellHouseDomain getSellHouseByChoose(SellHouseDoQuery sellHouseQueryDo, String city);

    /**
     * 查询二手房推荐房源
     * @param sellHouseQueryDo
     * @param city
     * @return
     */
    SellHouseDomain getRecommendSellHouse(SellHouseDoQuery sellHouseQueryDo, String city);


    /**
     * 二手房搜索结果列表页
     * @param sellHouseDoQuery
     * @return
     */
    SellHouseSearchDomain getSellHouseList(SellHouseDoQuery sellHouseDoQuery,String city);



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
