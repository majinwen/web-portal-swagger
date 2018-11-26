package com.toutiao.app.service.mapSearch;

import com.toutiao.app.domain.mapSearch.RentMapSearchDoQuery;
import com.toutiao.app.domain.mapSearch.RentMapSearchDomain;

public interface RentMapSearchRestService {
    /**
     * 地图找房-二手房查询
     * @param rentMapSearchDoQuery
     * @param city
     * @return
     */
    RentMapSearchDomain rentMapSearch(RentMapSearchDoQuery rentMapSearchDoQuery, String city);
}
