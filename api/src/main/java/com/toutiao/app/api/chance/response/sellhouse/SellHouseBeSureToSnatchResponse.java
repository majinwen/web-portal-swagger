package com.toutiao.app.api.chance.response.sellhouse;

import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.util.List;

@Data
public class SellHouseBeSureToSnatchResponse {
    /**
     *
     * top50社区标识
     */
    private  Integer isCommunityTopHouse;

    /**
     * 大楼名称
     */
    @ChangeName("buildingName")
    private  String  plotName;

    /**
     * 普通房源标题图
     */
    private  String housePhotoTitle;


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
     * 房源id
     */
    private  String houseId;

    /**
     * 排序字段
     */
    private  Long sort;


}
