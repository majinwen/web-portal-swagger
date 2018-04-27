package com.toutiao.app.domain.homepage;

import lombok.Data;

@Data
public class HomePageEsfDo {

    /**
     *楼盘名称
     */
    private  String plotNameAccurate;

    /**
     *房源标题
     */
    private  String claimHouseTitle;

    /**
     * 区域
     */
    private  String  area;

    /**
     * 商圈
     */
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
    private  String [] claimTagsName;


    /**
     * 面积
     */
    private  Double buildArea;


    /**
     * 房源id
     */
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
     * 城市id
     */
    private Integer cityId;


    /**
     *标题图
     */
    private  String claimHousePhotoTitle;
}
