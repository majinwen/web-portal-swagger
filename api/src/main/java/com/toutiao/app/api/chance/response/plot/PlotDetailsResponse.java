package com.toutiao.app.api.chance.response.plot;


import com.alibaba.fastjson.annotation.JSONField;
import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;
import java.util.Map;

import java.util.List;

@Data
public class PlotDetailsResponse {
    /**
     * 小区编号
     */
    @ChangeName("buildingId")
    private Integer id;
    /**
     * 小区名称/楼盘名称
     */
    @ChangeName("buildingName")
    private String rc;

    /**
     * 小区照片
     */
    @ChangeName("buildingImages")
    private List<String> photo;


    @ChangeName("districtName")
    private String area;

    /**
     * 商圈
     */
    @ChangeName("areaName")
    private String tradingArea;



    /**
     * 标签
     */
    @ChangeName("tags")
    private List<String> label;

    /**
     * 均价
     */
    @ChangeName("averagePrice")
    private Double avgPrice;

    /**
     * 建成年代
     */
    @ChangeName ("buildYears")
    private String abbreviatedAge;

    /**
     * 占地面积
     */
    @ChangeName("coversArea")
    private Double areaSize;
    /**
     * 建筑面积
     */
    @ChangeName("buildingArea")
    private Double buildingAreaSize;
    /**
     * 总栋数
     */
    @ChangeName("totalBuilding")
    private String sumBuilding;
    /**
     * 总户数
     */
    @ChangeName("totalHousehold")
    private String sumHousehold;
    /**
     * 绿化率
     */
    private Double avgGreening;

    /**
     * 电梯配备类型
     */
    private String elevator;
    /**
     * 车位配比
     */
    @ChangeName("parkRatio")
    private String carPositionRatio;
    /**
     * 停车费
     */
    private String parkingRate;

    /**
     * 物业公司
     */
    @ChangeName("propertyCompany")
    private String property;
    /**
     * 物业费
     */
    private Double propertyFee;


    /**
     * 供暖方式
     */
    private String heatingMode;

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
    private Double electricFee;
    /**
     * 供水
     */
    private String waterSupply;
    /**
     * 水费
     */
    private Double waterFee;

    /**
     * 详情环线
     */
    private String ringRoadName;

    /**
     * 位置
     */
    private  String location;


    /**
     * 电梯配备中文
     */
    @ChangeName("hasElevator")
    private String hasElevator;

}
