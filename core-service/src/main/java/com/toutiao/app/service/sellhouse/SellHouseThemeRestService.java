package com.toutiao.app.service.sellhouse;


import com.toutiao.app.domain.sellhouse.SellHouseThemeDoQuery;
import com.toutiao.app.domain.sellhouse.SellHouseThemeDomain;

public interface SellHouseThemeRestService {

    /**
     * 降价房
     * @param sellHouseThemeDoQuery
     * @param city
     * @return
     */
    SellHouseThemeDomain getCutPriceShellHouse(SellHouseThemeDoQuery sellHouseThemeDoQuery, String city);

    /**
     * 捡漏房
     * @param sellHouseThemeDoQuery
     * @param city
     * @return
     */
    SellHouseThemeDomain getLowPriceShellHouse(SellHouseThemeDoQuery sellHouseThemeDoQuery, String city);


    /**
     * 枪手房
     * @param sellHouseThemeDoQuery
     * @param city
     * @return
     */
    SellHouseThemeDomain getBeSureToSnatchShellHouse(SellHouseThemeDoQuery sellHouseThemeDoQuery, String city);


//    /**
//     * 二手房专题 订阅调用
//     * @param sellHouseThemeDoQuery
//     * @param city
//     * @return
//     */
//    SellHouseThemeDomain getSellHouseTheme(SellHouseThemeDoQuery sellHouseThemeDoQuery, String city);
}
