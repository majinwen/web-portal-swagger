package com.toutiao.app.service.plot;


import com.toutiao.app.domain.MapInfo;
import com.toutiao.app.domain.Plot.PlotDetailsDo;


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
}
