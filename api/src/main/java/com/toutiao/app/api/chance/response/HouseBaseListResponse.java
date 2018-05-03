package com.toutiao.app.api.chance.response;

import lombok.Data;

import java.util.Map;

/**
 * 房源列表基础返回实体类
 * 出售、出租房源
 */

@Data
public class HouseBaseListResponse {

    //楼层，电梯 标签 坐标 面积，居室 朝向

    /**
     * 房源id
     */
    private String houseId;
    /**
     * 楼盘id
     */
    private Integer buildingId;
    /**
     * 楼盘名称
     */
    private String buildingName;
    /**
     * 区域名称
     */
    private String districtName;
    /**
     * 商圈名称
     */
    private String areaName;
    /**
     * 朝向（1-东,2-西,3-南,4-北,5-东南,6-西南,7-东北,8-西北,9-东西,10-南北,11-其他）
     */
    private String forward;
    /**
     * 房源面积
     */
    private String houseArea;
    /**
     * 几室
     */
    private String room;
    /**
     * 几厅
     */
    private Integer hall;
    /**
     * 房源标题
     */
    private String houseTitle;
    /**
     * 房源标题图
     */
    private String housePhotoTitle;
    /**
     * 房源图片
     */
    private String[] housePhoto;
    /**
     * 地铁站编号
     */
    private String subwayStationId;
    /**
     * 地铁站名称
     */
    private String subwayStation;
    /**
     * 地铁到房源的距离
     */
    private Map subwayDistance;
    /**
     * 标签
     */
    private String[] tags;
    /**
     * 最近地铁站信息
     */
    private String nearBySubwayDesc;

}
