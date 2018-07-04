package com.toutiao.app.domain.sellhouse;

import lombok.Data;

import java.util.List;

@Data
public class SellHouseBeSureToSnatchDo {
    /**
     *
     * top50社区标识
     */
    private  Integer isCommunityTopHouse;

    /**
     * 大楼名称
     */
    private  String  plotName;

    /**
     * 普通房源标题图
     */
    private  String housePhotoTitle;

    /**
     * 认领房源标题图
     */
    private String claimHousePhotoTitle;


    /**
     * 室
     */
    private  Integer room;


    /**
     * 建筑面积
     */
    private Double buildArea;


    /**
     * 房源总价
     */
    private Double houseTotalPrices;


    /**
     *  降价标识
     */
    private  Integer isCutPrice;

    /**
     * 降幅
     */
    private  Double priceFloat;

    /**
     * 同小区同户型范围内做低价排名
     */
    private Integer rankInLowCommunityLayout;

    /**
     * 排序
     */
    private Object sort;

    /**
     * 房源id
     */
    private  String houseId;

    /**
     * 认领房源id
     */
    private  String claimHouseId;

    private  Integer isClaim;

    /**
     * 主力户型
     */
    private  Integer isMainLayout;

}
