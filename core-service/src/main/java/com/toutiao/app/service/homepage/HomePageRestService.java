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
     * 获取首页top50
     */

    List<HomePageTop50Do> getHomePageTop50();


    /**
     * 首页缝出必抢
     */
    List<HomeSureToSnatchDo>  getHomeBeSureToSnatch (HomeSureToSnatchDoQuery homeSureToSnatchDoQuery);
}
