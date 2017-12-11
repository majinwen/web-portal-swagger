package com.toutiao.web.dao.entity.admin;

import lombok.Data;

@Data
public class ProjHouseInfo {
    /**
     * 页面展示字段
     */
    // 房源id
    private Integer houseId;

    // 房源名称
    private String houseTitle;

    // 房源面积
    private String houseArea;

    // 房源户型
    private String houseType;

    // 房源户型id
    private String houseTypeId;

    // 房源朝向
    private String houseOrientation;

    // 房源朝向id
    private String houseOrientationId;

    // 房源总价
    private String houseTotalPrices;

    // 房源单价(52365元/㎡)
    private String houseUnitCost;

    // 房源标签(满二)
    private String houseLabel;

    // 房源标签(满二)
    private String houseLabelId;


    // 房源楼层
    private String houseFloorId;
    // 房源楼层
    private String houseFloor;

    // 房源楼龄
    private String houseYear;

    // 房源用途id
    private String housePurposeId;

    // 房源用途
    private String housePurpose;

    // 房源电梯id
    private String houseLiftId;
    // 房源电梯
    private String houseLift;

    // 房源供暖id
    private String houseHeatingId;
    // 房源供暖
    private String houseHeating;

    // 房源权属id
    private String houseOwnershipId;
    // 房源权属
    private String houseOwnership;

    // 房源推荐描述
    private String houseRecommendInfo;

    // 房源照片
    private String[] housePhoto;

    // 房源预算
    private String houseBudget;

    // 房源交通信息
    private String houseTrafficInfo;

    // 房源更新时间
    private String houseUpdateTime;

    // 物业类型id
    private String houseManagementTypeId;
    // 物业类型
    private String houseManagementType;

    // 建筑类别id
    private String houseBuildingTypeId;
    // 建筑类别
    private String houseBuildingType;


    // 房源级别(排序)
    private String houseRank;

    /**
     * 房源描述
     */
    // 经纪人头像
    private String houseProxyPhoto;
    // 经纪人姓名
    private String houseProxyName;
    // 经纪人电话号
    private String houseProxyPhone;
    // 房源介绍
    private String houseRecommend;
    /**
     * 小区信息 通过小区id或者名称获取 页面要显示小区名称 并且查询时候要讲小区id传递到后台
     */
    /**
     * 配套地图 通过小区地理坐标显示在百度地图上
     */
    // 房源小区id
    private Integer housePlotId;

    // 房源小区地理
    private Double[] housePlotLocation;

    // 房源小区名称
    private String housePlotName;

    // 房源小区信息
    private String housePlotInfo;

    /**
     * 附近好房 通过 获取当前房源所属小区内的房源信息
     */

    /**
     * 附近小区 通过小区地理坐标查询附近小区 （算距离）
     */
    /**
     * 点击地理标签需要存储的字段
     */
    // 商圈名称
    private String houseBusinessNameId;

    // 商圈名称
    private String houseBusinessName;

    // 区域id
    private String areaId;

    // 区域名称(朝阳)
    private String areaName;

    // 地铁线id
    private String[] subwayLineId;

    // 地铁线名称
    private String[] subwayLineName;

    // 地铁站id
    private String[] subwayStationId;

    // 地铁站名称
    private String[] subwayStationName;


}