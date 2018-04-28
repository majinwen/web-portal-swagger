package com.toutiao.app.domain.sellhouse;

import lombok.Data;

import java.util.Map;

@Data
public class SellHouseDo {

    /**
     * 二手房房源id
     */
    private Integer claimHouseId;
    /**
     * 房源标题
     */
    private String claimHouseTitle;

    /**
     * 标题图
     */
    private String claimHousePhotoTitle;

    /**
     * 加个涨降标志
     */
    private String priceIncreaseDecline;

    /**
     * 房源总价
     */
    private Double houseTotalPrices;

    /**
     * 房源总价
     */
    private Double houseUnitCost;

    /**
     * 建筑面积
     */
    private Double buildArea;

    /**
     * 标签名称(1:近地铁 4:随时看 8:满二年 16:满五年 32:近公园)
     */
    private String[] claimTagsName;

    /**
     * 室
     */
    private Integer room;
    /**
     * 厅
     */
    private Integer hall;

    /**
     * 朝向
     */
    private String forwardName;

    /**
     * 区域名称
     */
    private String area;

    /**
     * 商圈名称
     */
    private String houseBusinessName;

    /**
     * 小区名称
     */
    private String plotName;

    /**
     * 地铁线/站 距离
     */
    private Map<String, String> subwayDistince;
    /**
     * 建成年代
     */
    private String year;
    /**
     * 房源小区地理坐标
     */
    private String housePlotLocation;

    /**
     * 车位配比
     */
    private String parkRadio;




}
