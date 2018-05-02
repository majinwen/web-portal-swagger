package com.toutiao.app.api.chance.response.homepage;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class HomePageEsfResponse {

    /**
     *楼盘名称
     */
    @JSONField(name="buildingName")
    private  String plotNameAccurate;

    /**
     *房源标题
     */
    @JSONField(name="houseTitle")
    private  String claimHouseTitle;

    /**
     * 区域
     */
    @JSONField(name="districtName")
    private  String  area;

    /**
     * 商圈
     */
    @JSONField(name="areaName")
    private  String houseBusinessName;

    /**
     * 室
     */
    private  Integer room;

    /**
     * 厅
     */
    private  Integer hall;

    /**
     * 卫
     */
    private  Integer toilet;

    /**
     * 厨
     */
    private  Integer kitchen;

    /**
     * 标签
     */
   @JSONField(name="tags")
    private  String [] claimTagsName;

    /**
     * 面积
     */
    private  Double buildArea;


    /**
     * 房源id
     */
    @JSONField(name="houseId")
    private  String claimHouseId;


    /**
     * 总价
     */
    private  Double  houseTotalPrices;

    /**
     * 朝向
     */
    private  String forwardName;


    /**
     * 单价
     */
    private  Double houseUnitCost;

    /**
     * 标题图
     */
    @JSONField(name="housePhotoTitle")
    private  String claimHousePhotoTitle;
}
