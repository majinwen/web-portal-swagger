package com.toutiao.app.service.plot;


import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.domain.plot.*;

import java.lang.reflect.InvocationTargetException;
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
     * @param plotListQuery
     * @return
     */
    PlotListDo queryPlotListByRequirement(PlotListQuery plotListQuery);

    /**
     * 获取小区列表含坐标
     * @param plotListQuery
     * @return
     */
    List<PlotDetailsFewDo> queryPlotListByRequirementWithLocation(PlotListQuery plotListQuery);


    /**
     * 获取交通配套
     */
    PlotTrafficDo queryPlotDataInfo(Integer plotId) throws InvocationTargetException, IllegalAccessException;

    /**
     * 小区收藏列表
     * @param list
     * @return
     */
    PlotDetailsFewDomain queryPlotListByPlotIdList(List list, Integer pageNum, Integer size);




}
