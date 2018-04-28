package com.toutiao.app.api.chance.response;


import lombok.Data;

import java.util.List;

/**
 * 楼盘详情基础返回实体类
 * 新楼，小区
 */

@Data
public class BuildingBaseResponse {

    /**
     * 楼盘id
     */
    private Integer buildingId;
    /**
     * 楼盘名称
     */
    private String buildingName;
    /**
     * 楼盘昵称
     */
    private String buildingNickName;
    /**
     * 楼盘轮播图
     */
    private List<String> buildingImages;
    /**
     * 区域名称
     */
    private String districtName;
    /**
     * 商圈名称
     */
    private String areaName;
    /**
     * 楼盘参考均价(元/m2)
     */
    private Double averagePrice;
    /**
     * 总价
     */
    private Double totalPrice;
    /**
     * 楼盘标签
     */
    private String[] tags;
    /**
     * 绿化率
     */
    private Double avgGreening;
    /**
     * 最近地铁站信息
     */
    private String nearBySubwayDesc;
    /**
     * 建成年份
     */
    private String buildYears;
    /**
     * 总栋数
     */
    private Integer totalBuilding;
    /**
     * 总户数
     */
    private Integer totalHousehold;
    /**
     * 车位配比
     */
    private String parkRatio;
    /**
     * 收藏数量
     */
    private Integer favoriteCount;
    /**
     * 是否被收藏
     */
    private Integer isFavorite;
    /**
     * 环线
     */
    private String ringRoad;
    /**
     * 详情环线
     */
    private String ringRoadDesc;
    /**
     * 详情环线距离
     */
    private Integer ringRoadDistance;
    /**
     * 供电
     */
    private String electricSupply;
    /**
     * 供水
     */
    private String waterSupply;
    /**
     * 占地面积
     */
    private String coversArea;
    /**
     * 建筑面积
     */
    private String buildingArea;
    /**
     * 容积率
     */
    private Double dimension;
    /**
     * 物业公司
     */
    private String propertyCompany;
    /**
     * 物业类型名称
     */
    private String propertyType;
    /**
     * 物业费
     */
    private String propertyFee;
    /**
     * 供暖方式
     */
    private String heatingMode;


}
