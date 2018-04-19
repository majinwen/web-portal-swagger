package com.toutiao.app.service.plot;


import com.toutiao.app.domain.plot.PlotDetailsDo;
import com.toutiao.app.domain.plot.PlotDetailsFewDo;
import com.toutiao.app.domain.plot.PlotListDo;

import java.util.List;


public interface PlotsRestService {
    /**
     * 根据小区id查询小区详情
     * @param plotId
     * @return
     */
    PlotDetailsDo queryPlotDetailByPlotId(Integer plotId);


    /**
     * 获取小区周围小区
     * @param lat
     * @param lon
     * @return
     */
    List<PlotDetailsFewDo> queryAroundPlotByLocation(Double lat, Double lon, Integer plotId);

    /**
     * 获取小区列表
     * @param plotListDo
     * @return
     */
    List<PlotDetailsFewDo> queryPlotListByRequirement(PlotListDo plotListDo);

    /**
     * 获取小区列表含坐标
     * @param plotListDo
     * @return
     */
    List<PlotDetailsFewDo> queryPlotListByRequirementWithLocation(PlotListDo plotListDo);



}
