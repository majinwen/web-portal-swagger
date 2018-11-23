package com.toutiao.app.service.mapSearch;

import com.toutiao.app.domain.mapSearch.EsfMapSearchDoQuery;

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
    Object esfMapSearch(EsfMapSearchDoQuery esfMapSearchDoQuery, String city);
}
