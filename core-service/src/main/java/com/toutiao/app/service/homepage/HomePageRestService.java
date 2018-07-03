package com.toutiao.app.service.homepage;

import com.toutiao.app.domain.homepage.*;
import com.toutiao.app.domain.newhouse.NewHouseListDomain;

import java.util.List;

public interface HomePageRestService {

    /**
     * 获取首页二手房5条
     */

    List<HomePageEsfDo> getHomePageEsf();


    /**
     * 获取新房五条
     */
    NewHouseListDomain getHomePageNewHouse();

    /**
     * 获取首页主题房
     */
    HomeThemeHouseListDo getHomeThemeHouse(HomeThemeHouseDoQuery homeThemeHouseDoQuery);

    /**
     * 首页附近小区
     */
    HomePageNearPlotListDo getHomePageNearPlot(NearHouseDoQuery nearHouseDoQuery);

    /**
     * 首页附近二手房
     * @param nearHouseDoQuery
     * @return
     */
    HomePageNearEsfListDo getHomePageNearEsf(NearHouseDoQuery nearHouseDoQuery);

    /**
     * 专题着陆页-附近小区
     * @param plotId
     * @return
     */
    HomePageNearPlotDo getPlotSpecialPage(Integer plotId);

    /**
     * 专题着陆页-附近二手房
     * @param nearHouseSpecialPageDoQuery
     * @return
     */
    HomePageNearEsfListDo getEsfSpecialPage(NearHouseSpecialPageDoQuery nearHouseSpecialPageDoQuery);
}
