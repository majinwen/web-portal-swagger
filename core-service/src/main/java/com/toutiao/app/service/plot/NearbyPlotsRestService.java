package com.toutiao.app.service.plot;


import com.toutiao.app.domain.plot.NearbyPlotsListDo;
import com.toutiao.app.domain.plot.PlotDetailsFewDo;

import java.util.List;

public interface NearbyPlotsRestService {

    /**
     * 根据用户坐标获取用户附近小区列表
     * @param nearbyPlotsListDo
     * @return
     */
    List<PlotDetailsFewDo> queryNearbyPlotsListByUserCoordinate(NearbyPlotsListDo nearbyPlotsListDo);

}
