package com.toutiao.web.dao.entity.admin;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Map;

@Data
public class ProjHouseInfoES {
    /**
     * 页面展示字段
     */
    // 房源id
    @JSONField(name = "houseId")
    private Integer houseId;

//    //房源编号
//    @JSONField()
//    private String houseNumber;

    // 房源名称
    @JSONField(name = "houseTitle")
    private String houseTitle;

    // 房源面积
    @JSONField(name = "buildArea")  //建筑面积
    private String houseArea;

    // 房源户型
    @JSONField(name = "room")
    private String houseType;

    // 房源户型id
    @JSONField(name = "")
    private String houseTypeId;

    // 房源朝向
//    @JSONField(name = ")
    private String houseOrientation;

    // 房源朝向id
    @JSONField(name = "forward")
    private String houseOrientationId;

    // 房源总价
//    @JSONField(name = "")
    private String houseTotalPrices;

    // 房源单价(52365元/㎡)
    @JSONField(name = "price")
    private String houseUnitCost;

    // 房源标签(满二)
//    @JSONField(name = "")
    private String[] houseLabel;

    // 房源标签(满二)
    @JSONField(name = "tags")
    private String[] houseLabelId;

    // 房源楼层
    @JSONField(name = "floorNo")
    private String houseFloorId;
    // 房源楼层
//    @JSONField(name = "")
    private String houseFloor;

    // 房源楼龄
//    @JSONField(name = "")
    private String houseYear;

    // 房源用途，
    @JSONField(name = "houseType")
    private String housePurposeId;

    // 房源用途
//    @JSONField(name = "")
    private String housePurpose;

    // 房源电梯id
//    @JSONField(name = "")
    private String houseLiftId;
    // 房源电梯
//    @JSONField(name = "")
    private String houseLift;

    // 房源供暖id
//    @JSONField(name = "")
    private String houseHeatingId;
    // 房源供暖
//    @JSONField(name = "")
    private String houseHeating;

    // 房源权属id
//    @JSONField(name = "")
    private String houseOwnershipId;
    // 房源权属
//    @JSONField(name = "")
    private String houseOwnership;

    // 房源推荐描述
    @JSONField(name = "recommendReason")
    private String houseRecommendInfo;

    // 房源照片
//    @JSONField(name = "")
    private String[] housePhoto;

    // 房源预算
//    @JSONField(name = "")
    private String houseBudget;

    // 房源交通信息
    @JSONField(name = "traffic")
    private String houseTrafficInfo;

    // 房源更新时间
    @JSONField(name = "updateTime")
    private String houseUpdateTime;

    // 物业类型id
    @JSONField(name = "propertyType")
    private String houseManagementTypeId;
    // 物业类型
//    @JSONField(name = "")
    private String houseManagementType;

    // 建筑类别id
    @JSONField(name = "buildCategory")
    private String houseBuildingTypeId;
    // 建筑类别
//    @JSONField(name = "")
    private String houseBuildingType;

    // 房源级别(排序)
    @JSONField(name = "houseLevel")
    private String houseRank;
    /**
     * 房源描述
     */
    // 经纪人头像
//    @JSONField(name = "")
    private String houseProxyPhoto;
    // 经纪人姓名
//    @JSONField(name = "")
    private String houseProxyName;
    // 经纪人电话号
//    @JSONField(name = "")
    private String houseProxyPhone;
    // 房源介绍
//    @JSONField(name = "")
    private String houseRecommend;
    /**
     * 小区信息 通过小区id或者名称获取 页面要显示小区名称 并且查询时候要讲小区id传递到后台
     */
    /**
     * 配套地图 通过小区地理坐标显示在百度地图上
     */
    // 房源小区id
    @JSONField(name = "newcode")
    private Integer housePlotId;

    // 房源小区地理坐标
//    @JSONField(name = "")
    private String housePlotLocation;

    // 房源小区名称
//    @JSONField(name = "")
    private String housePlotName;

    //房源小区照片
//    @JSONField(name = "")
    private String[] housePlotPhoto;

    // 房源小区信息
//    @JSONField(name = "")
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
    // 商圈名称ID
//    @JSONField(name = "")
    private String houseBusinessNameId;

    // 商圈名称
//    @JSONField(name = "")
    private String houseBusinessName;

    // 区域id
//    @JSONField(name = "")
    private String areaId;

    // 区域名称(朝阳)
//    @JSONField(name = "")
    private String areaName;

    // 地铁线id
//    @JSONField(name = "")
    private String[] subwayLineId;

    // 地铁线名称
//    @JSONField(name = "")
    private String[] subwayLineName;

    // 地铁站id
//    @JSONField(name = "")
    private String[] subwayStationId;

    // 地铁站名称
//    @JSONField(name = "")
    private String[] subwayStationName;

    //距离您多少公里
//    @JSONField(name = "")
    private Map<String, String> houseToSubwayDistance;

//    @JSONField(name = "")
    private String key;

    //版本控制
    private int version;

}