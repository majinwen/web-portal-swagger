package com.toutiao.web.service.newhouse;

import com.toutiao.web.domain.query.NewHouseQuery;

import java.util.Map;

public interface NewHouseService {


    /**
     * 根绝条件筛选新房
     * @param newHouseQuery
     * @return
     */
    Map<String,Object> getNewHouse(NewHouseQuery newHouseQuery);

    /**
     * 获取楼盘详情信息
     * @param buildingId
     * @return
     */
    Map<String,Object> getNewHouseDetails(Integer buildingId);

    /**
     * 获取楼盘参数详情
     * @param buildingId
     * @return
     */
    Map<String,Object> getNewHouseParameterDetails(Integer buildingId);


    /**
     * 获取楼盘户型详情
     * @param buildingId
     * @return
     */
    Map<String,Object> getNewHouseLayoutDetails(Integer buildingId, Integer tags);


    /**
     * 获取楼盘户型居室数量
     * @param buildingId
     * @return
     */
    Map<String,Object> getNewHouseLayoutCountByRoom(Integer buildingId);
    /**
     * 获取楼盘全部描述
     * @return
     */
    List<Map<String,Object>>getNewHouseDiscript(Integer id);

}
