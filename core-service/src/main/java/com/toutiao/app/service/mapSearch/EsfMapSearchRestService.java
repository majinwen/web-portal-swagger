package com.toutiao.app.service.mapSearch;

import com.toutiao.app.domain.mapSearch.*;

/**
 * @ClassName EsfMapSearchRestService
 * @Author jiangweilong
 * @Date 2018/11/23 3:32 PM
 * @Description:
 **/
public interface EsfMapSearchRestService {

    /**
     * 地图找房-二手房查询
     * @param esfMapSearchDoQuery
     * @return
     */
    EsfMapSearchDomain esfMapSearch(EsfMapSearchDoQuery esfMapSearchDoQuery, String city);

    /**
     * 二手房-小区房源列表
     * @param esfMapSearchDoQuery
     * @param city
     * @return
     */
    EsfHouseListDomain esfMapSearchHouseList(EsfMapSearchDoQuery esfMapSearchDoQuery, String city);

    /**
     * 地图找房-地铁找房
     * @param esfMapSearchDoQuery
     * @param city
     * @return
     */
    EsfMapStationDomain esfMapSubwaySearch(EsfMapSearchDoQuery esfMapSearchDoQuery, String city);

    /**
     * 画圈找房房源列表
     * @param esfMapSearchDoQuery
     * @param city
     * @return
     */
    EsfCircleListDomain esfMapDrawCircleList(EsfMapSearchDoQuery esfMapSearchDoQuery, String city);
}
