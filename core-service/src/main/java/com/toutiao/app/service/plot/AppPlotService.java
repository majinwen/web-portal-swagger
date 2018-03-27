package com.toutiao.app.service.plot;


import com.toutiao.app.domain.MapInfo;
import com.toutiao.app.domain.Plot.PlotDetailsDo;

import java.util.List;


public interface AppPlotService {
    /**
     * 根据小区id查询小区详情
     * @param plotId
     * @return
     */
    PlotDetailsDo queryPlotDetailByPlotId(Integer plotId);

    /**
     * 获取小区周边配套
     * @param plotId
     * @return
     */
    MapInfo queryPlotMapInfo(Integer plotId);

    /**
     * 获取小区周围小区
     * @param lat
     * @param lon
     * @return
     */
    List<PlotDetailsDo> queryAroundPlotByLocation(Double lat,Double lon,Integer plotId);
}
