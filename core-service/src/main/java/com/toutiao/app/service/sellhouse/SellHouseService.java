package com.toutiao.app.service.sellhouse;


import com.toutiao.app.domain.message.MessageSellHouseDo;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.app.domain.sellhouse.*;

import java.util.Date;
import java.util.List;


public interface SellHouseService {

    /**
     * 二手房房源详情
     * @param houseId
     * @return
     */
    SellHouseDetailsDo getSellHouseByHouseId(String houseId,String city);

    /**
     * 二手房房源详情消息推送
     * @param houseId
     * @return
     */
    List<MessageSellHouseDo> querySellHouseByHouseId(String[] houseId);

    /**
     * 二手房房源详情消息推送
     * @param houseId
     * @param city
     * @return
     */
    List<MessageSellHouseDo> querySellHouseByHouseIdNew(String[] houseId, String city);

//    /**
//     * 认领二手房房源经纪人
//     * @param houseId
//     * @return
//     */
//    AgentsBySellHouseDo getAgentByHouseId(Integer houseId);

    /**
     * 条件查询二手房房源
     * @param sellHouseQueryDo
     * @param city
     * @return
     */
    SellHouseDomain getSellHouseByChoose(SellHouseDoQuery sellHouseQueryDo, String city);

    /**
     * 条件查询二手房房源V1
     * @param userFavoriteConditionDoQuery
     * @param city
     * @return
     */
    SellHouseDomain getSellHouseByChooseV1(UserFavoriteConditionDoQuery userFavoriteConditionDoQuery, String city);

    /**
     * 条件查询二手房房源(无预设条件)
     * @param userFavoriteConditionDoQuery
     * @param city
     * @return
     */
    SellHouseDomain getSellHouseNoCondition(UserFavoriteConditionDoQuery userFavoriteConditionDoQuery, String city);

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
     * 缝出必抢专题页
     * @param sellHouseBeSureToSnatchDoQuery
     * @return
     */
    SellHouseBeSureToSnatchDomain getBeSureToSnatchList(SellHouseBeSureToSnatchDoQuery sellHouseBeSureToSnatchDoQuery, String city);

    /**
     * 推荐房源
     *
     * @param recommendEsf5DoQuery
     * @return
     */
    SellHouseSearchDomain getRecommendEsf5(RecommendEsf5DoQuery recommendEsf5DoQuery, String city);

    /**
     * 二手房判断是否上传默认图
     * @param importTime
     * @param today
     * @param image
     * @return
     */
    int isDefaultImage(String importTime, Date today, String image);
}
