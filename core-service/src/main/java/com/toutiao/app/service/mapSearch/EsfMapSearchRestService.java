package com.toutiao.app.service.mapSearch;

import com.toutiao.app.domain.mapSearch.EsfMapSearchDoQuery;
import com.toutiao.app.domain.mapSearch.EsfMapSearchDomain;

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
}
