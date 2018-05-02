package com.toutiao.app.api.chance.response.plot;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class PlotDetailsResponse {
    /**
     * 小区编号
     */
    @JSONField(name="buildingId")
    private Integer id;
    /**
     * 小区名称/楼盘名称
     */
    @JSONField(name="buildingName")
    private String rc;
    /**
     * 小区照片
     */
    @JSONField(name="buildingImages")
    private  List<String> photo;

    /**
     * 区域
     */
    @JSONField(name="districtName")
    private String area;

    /**
     * 商圈
     */
    @JSONField(name="areaName")
    private String tradingArea;
    /**
     * 地址
     */
    private String address;
    /**
     * 坐标
     */
    private String location;

    /**
     * 交通信息
     */
    private String TrafficInformation;
    /**
     * 标签
     */
    @JSONField(name="tags")
    private  List<String> label;

    /**
     * 均价
     */
    @JSONField(name="averagePrice")
    private Integer avgPrice;

    /**
     * 建成年代
     */
    @JSONField(name="buildYears")
    private String abbreviatedAge;

    /**
     * 占地面积
     */
    @JSONField(name="coversArea")
    private String areaSize;
    /**
     * 建筑面积
     */
    @JSONField(name="buildingArea")
    private String buildingAreaSize;
    /**
     * 总栋数
     */
    @JSONField(name="totalBuilding")
    private String sumBuilding;
    /**
     * 总户数
     */
    @JSONField(name="totalHousehold")
    private String sumHousehold;
    /**
     * 户均绿化率
     */
    @JSONField(name="avgGreening")
    private Double avgGreening;

    /**
     * 电梯配备
     */
    private String elevator;
    /**
     * 车位配比
     */
    @JSONField(name="parkRatio")
    private String carPositionRatio;
    /**
     * 停车费
     */
    private String parkingRate;


    /**
     * 物业公司
     */
    @JSONField(name="propertyCompany")
    private String property;
    /**
     * 物业费
     */
    @JSONField(name="propertyFee")
    private String propertyFee;
    /**
     * 物业类型名称
     */
    @JSONField(name="propertyType")
    private String propertyTypeName;

    /**
     * 供暖方式
     */
    @JSONField(name="heatingMode")
    private String heatingMode;


    /**
     * 容积率
     */
    @JSONField(name="dimension")
    private Double dimension;
    /**
     * 空气质量
     */
    private String airQuality;
    /**
     * 供电
     */
    @JSONField(name="electricSupply")
    private String electricSupply;

    /**
     * 供水
     */
    @JSONField(name="waterSupply")
    private String waterSupply;

    /**
     * 环线
     */
    @JSONField(name="ringRoad")
    private String ringRoadName;

    /**
     * 建筑类型
     */
    private  String buildingStructure;

}
