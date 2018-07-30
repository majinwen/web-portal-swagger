package com.toutiao.app.service.plot;


import com.toutiao.app.domain.plot.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


public interface PlotsRestService {
    /**
     * 根据小区id查询小区详情
     * @param plotId
     * @return
     */
    PlotDetailsDo queryPlotDetailByPlotId(Integer plotId,String userAgent , String city);


    /**
     * 获取小区周围小区
     * @param lat
     * @param lon
     * @return
     */
    List<PlotDetailsFewDo> queryAroundPlotByLocation(Double lat, Double lon, Integer plotId);

    /**
     * 获取小区列表
     * @param plotListDoQuery
     * @return
     */
    PlotListDo queryPlotListByRequirement(PlotListDoQuery plotListDoQuery, HttpServletRequest request, HttpServletResponse response,String userAgent,  String city);

    /**
     * 获取小区列表含坐标
     * @param plotListDoQuery
     * @return
     */
    List<PlotDetailsFewDo> queryPlotListByRequirementWithLocation(PlotListDoQuery plotListDoQuery);


    /**
     * 获取交通配套
     */
    PlotTrafficDo queryPlotDataInfo(Integer plotId,String userAgent , String city) throws InvocationTargetException, IllegalAccessException;

    /**
     * 小区收藏列表
     * @param list
     * @return
     */
    PlotFavoriteListDo queryPlotListByPlotIdList(List list, Integer pageNum, Integer size);

    /**
     * 小区top50查询
     */

    List<PlotTop50Do> getPlotTop50List(PlotTop50ListDoQuery plotTop50ListDoQuery);


}
