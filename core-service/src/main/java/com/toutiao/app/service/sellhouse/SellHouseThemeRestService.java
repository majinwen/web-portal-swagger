package com.toutiao.app.service.sellhouse;


import com.toutiao.app.domain.sellhouse.*;

public interface SellHouseThemeRestService {

    /**
     * 降价房
     * @param sellHouseThemeDoQuery
     * @param city
     * @return
     */
    CutPriceSellHouseThemeDomain getCutPriceShellHouse(SellHouseThemeDoQuery sellHouseThemeDoQuery, String city);

    /**
     * 捡漏房
     * @param sellHouseThemeDoQuery
     * @param city
     * @return
     */
    LowPriceSellHouseThemeDomain getLowPriceShellHouse(SellHouseThemeDoQuery sellHouseThemeDoQuery, String city);


    /**
     * 枪手房
     * @param sellHouseThemeDoQuery
     * @param city
     * @return
     */
    HotSellHouseThemeDomain getBeSureToSnatchShellHouse(SellHouseThemeDoQuery sellHouseThemeDoQuery, String city);


//    /**
//     * 二手房专题 订阅调用
//     * @param sellHouseThemeDoQuery
//     * @param city
//     * @return
//     */
//    SellHouseThemeDomain getSellHouseTheme(SellHouseThemeDoQuery sellHouseThemeDoQuery, String city);
}
