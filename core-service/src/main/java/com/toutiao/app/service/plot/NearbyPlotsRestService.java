package com.toutiao.app.service.plot;


import com.toutiao.app.domain.plot.NearbyPlotsDoQuery;
import com.toutiao.app.domain.plot.PlotDetailsFewDomain;


public interface NearbyPlotsRestService {

    /**
     * 根据用户坐标获取用户附近小区列表
     * @param nearbyPlotsDoQuery
     * @return
     */
    PlotDetailsFewDomain queryNearbyPlotsListByUserCoordinate(NearbyPlotsDoQuery nearbyPlotsDoQuery);

}
