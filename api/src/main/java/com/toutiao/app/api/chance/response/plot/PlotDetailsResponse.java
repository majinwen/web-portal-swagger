package com.toutiao.app.api.chance.response.plot;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import java.util.Map;


@Data
public class PlotDetailsResponse {
    /**
     * 小区编号
     */
//    @JSONField(name = "buildingId")
    private Integer id;
    /**
     * 小区名称/楼盘名称
     */
//    @JSONField(name = "buildingName")
    private String rc;
    /**
     * 别名
     */
//    @JSONField(name = "buildingNickName")
    private String alias;
    /**
     * 小区照片
     */
//    @JSONField(name = "buildingImages")
    private String[] photo;
    /**
     * 区域编号
     */
//    @JSONField(name = "districtId")
    private String areaId;
    /**
     * 区域
     */
//    @JSONField(name = "districtName")
    private String area;
    /**
     * 商圈编号
     */
//    @JSONField(name = "areaId")
    private String tradingAreaId;
    /**
     * 商圈
     */
//    @JSONField(name = "areaName")
    private String tradingArea;
    /**
     * 地址
     */
//    @JSONField(name = "houseAddress")
    private String address;
//    /**
//     * 坐标
//     */
//    private String location;
//    /**
//     * 地铁站编号
//     */
//    private String[] metroStationId;
//    /**
//     * 地铁站
//     */
//    private String[] metroStation;
//    /**
//     * 地铁站与小区的距离
//     */
//    private Map metroWithPlotsDistance;
//    /**
//     * 地铁线路编号
//     */
//    private String[] subwayLineId;
//    /**
//     * 地铁线路
//     */
//    private String[] subwayLine;
    /**
     * 交通信息
     */
//    @JSONField(name = "nearBySubwayDesc")
    private String TrafficInformation;
//    /**
//     * 标签编号
//     */
//    private String[] labelId;
    /**
     * 标签
     */
//    @JSONField(name = "tags")
    private String[] label;
//    /**
//     * 待售房源
//     */
//    private String[] saleHouse;
    /**
     * 均价
     */
//    @JSONField(name = "averagePrice")
    private Integer avgPrice;
    /**
     * 总价
     */
//    @JSONField(name = "totalPrice")
    private Integer sumPrice;
    /**
     * 建成年代
     */
//    @JSONField(name = "buildYears")
    private String abbreviatedAge;
//    /**
//     * 楼龄
//     */
//    private Integer age;
    /**
     * 占地面积
     */
//    @JSONField(name = "coversArea")
    private String areaSize;
    /**
     * 建筑面积
     */
//    @JSONField(name = "buildingArea")
    private String buildingAreaSize;
    /**
     * 总栋数
     */
//    @JSONField(name = "totalBuilding")
    private String sumBuilding;
    /**
     * 总户数
     */
//    @JSONField(name = "totalHousehold")
    private String sumHousehold;
    /**
     * 绿化率
     */
    private Double avgGreening;
    /**
     * 梯户比
     */
    private Double liftDoorRadio;
    /**
     * 户均电梯
     */
    private String avgElevator;
    /**
     * 电梯配备
     */
//    @JSONField(name = "hasElevator")
    private String elevator;
    /**
     * 车位配比
     */
//    @JSONField(name = "parkRatio")
    private String carPositionRatio;
    /**
     * 停车费
     */
    private String parkingRate;
    /**
     * 小区详情介绍
     */
//    @JSONField(name = "houseDesc")
    private String desc;
    /**
     * 小区特色
     */
//    @JSONField(name = "feature")
    private String characteristic;
    /**
     * 开发商
     */
    private String developers;
    /**
     * 物业公司
     */
//    @JSONField(name = "propertyCompany")
    private String property;
    /**
     * 物业费
     */
    private String propertyFee;
    /**
     * 物业类型
     */
    private String propertyType;
    /**
     * 产权年限
     */
    private String yopr;
    /**
     * 建筑类型编号
     */
    private String[] architectureTypeId;
    /**
     * 建筑类型
     */
    private String[] architectureType;
    /**
     * 建筑结构
     */
    private String buildingStructure;
    /**
     * 住宅类型
     */
    private String residentialType;
    /**
     * 供暖方式
     */
    private String heatingMode;
    /**
     * 供需关系
     */
    private String sdr;
    /**
     * 楼盘级别
     */
    private Integer level;
    /**
     * 楼盘特色
     */
    private String villageCharacteristics;
    /**
     * 列表页展示商圈或者与地铁站的距离
     */
    private String key;
    /**
     * 城市编号
     */
    private String cityId;
    /**
     * 环线
     */
    private String ringRoad;
    /**
     * 容积率
     */
    private Double dimension;
    /**
     * 空气质量
     */
    private String airQuality;
    /**
     * 供电
     */
    private String electricSupply;
    /**
     * 电费
     */
    private String electricFee;
    /**
     * 供水
     */
    private String waterSupply;
    /**
     * 水费
     */
    private String waterFee;
    /**
     * 装修标准
     */
    private String decorationType;
    /**
     * 详情环线
     */
    private String ringRoadName;
    /**
     * 详情环线距离
     */
    private Integer ringRoadDistance;
    /**
     * 同比
     */
    private Double tongbi;
    /**
     * 环比
     */
    private Double huanbi;
    /**
     * 总数
     */
    private Long total;
    /**
     * 页数
     */
    private Integer pageNum;

//    /**
//     * 小区内二手房
//     */
//    private List<SellHouseDetailsDo> sellHouseDetailsDo;
//
//    /**
//     * 小区内出租房源
//     */
//    private List<RentDetailsDo> rentDetailsDo;
//
//    /**
//     * 小区周边配套
//     */
//    private MapInfo mapInfo;
//
//    /**
//     * 附近小区
//     */
//    private List<PlotDetailsDo> plotDetailsDoList;
}
