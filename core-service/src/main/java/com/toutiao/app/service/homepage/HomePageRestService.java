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
    HomePageNearPlotListDo getHomePageNearPlot(NearPlotDoQuery nearPlotDoQuery);

}
