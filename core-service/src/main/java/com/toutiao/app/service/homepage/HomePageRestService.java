package com.toutiao.app.service.homepage;

import com.toutiao.app.domain.homepage.*;
import com.toutiao.app.domain.newhouse.NewHouseListDomain;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDo;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;

import java.util.List;
import java.util.Map;

public interface HomePageRestService {

    /**
     * 获取首页二手房5条
     */

    List<HomePageEsfDo> getHomePageEsf();


    /**
     * 获取新房五条
     */
    NewHouseListDomain getHomePageNewHouse(String city);

    /**
     * 获取首页主题房
     */
    HomeThemeHouseListDo getHomeThemeHouse(HomeThemeHouseDoQuery homeThemeHouseDoQuery);

    /**
     * 首页附近小区
     */
    HomePageNearPlotListDo getHomePageNearPlot(NearHouseDoQuery nearHouseDoQuery,String city);

    /**
     * 首页附近二手房
     * @param nearHouseDoQuery
     * @return
     */
    HomePageNearEsfListDo getHomePageNearEsf(NearHouseDoQuery nearHouseDoQuery, String city);

    /**
     * 专题着陆页-附近小区
     * @param nearHouseSpecialPageDoQuery
     * @return
     */
    HomePageNearPlotDo getPlotSpecialPage(NearHouseSpecialPageDoQuery nearHouseSpecialPageDoQuery, String city);

    /**
     * 专题着陆页-附近二手房
     * @param nearHouseSpecialPageDoQuery
     * @return
     */
    HomePageNearEsfListDo getEsfSpecialPage(NearHouseSpecialPageDoQuery nearHouseSpecialPageDoQuery, String city);

    /**
     * 首页获取不买亏二手房8条
     *
     * @param topicType 主题类型：1-降价房, 2-捡漏房
     */
    List<HomePageMustBuyDo> getHomePageMustBuy(Integer topicType);

    /**
     * 获取首页top50
     */

//    List<HomePageTop50Do> getHomePageTop50();
    Map<String,HomePageTop50Do> getHomePageTop50();


    /**
     * 首页缝出必抢
     */
    List<HomeSureToSnatchDo>  getHomeBeSureToSnatch (HomeSureToSnatchDoQuery homeSureToSnatchDoQuery);

    /**
     * 保存推荐条件
     * @return
     */
    Integer saveRecommendCondition(UserFavoriteConditionDoQuery userFavoriteConditionDoQuery, String city);

    /**
     * 获取推荐条件
     */
    UserFavoriteConditionDo getRecommendCondition(Integer userId, String city);

    /**
     * 更新推荐条件
     * @param userFavoriteConditionDoQuery
     * @return
     */
    Integer updateRecommendCondition(UserFavoriteConditionDoQuery userFavoriteConditionDoQuery, String city);

    /**
     * 删除推荐条件
     * @param userId
     * @return
     */
    Integer deleteRecommendCondition (Integer userId, Integer cityId);
}
