package com.toutiao.app.api.chance.response.sellhouse;

import lombok.Data;

import java.util.Map;

@Data
public class SellHouseResponse {

    /**
     * 二手房房源id
     */
    private Integer houseId;
    /**
     * 房源标题
     */
    private String houseTitle;
    /**
     * 室
     */
    private Integer room;
    /**
     * 厅
     */
    private Integer hall;
    /**
     * 区域名称
     */
    private String area;
    /**
     * 区域id
     */
    private Integer areaId;
    /**
     * 商圈id
     */
    private Integer houseBusinessNameId;
    /**
     * 商圈名称
     */
    private String houseBusinessName;
    /**
     * 楼盘ID(楼盘/小区)
     */
    private Integer newCode;
    /**
     * 小区名称
     */
    private String plotName;
    /**
     * 地铁站id
     */
    private String[] subwayStationId;
    /**
     * 地铁线id
     */
    private Integer[] subwayLineId;
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
     * 标签名称(1:近地铁 4:随时看 8:满二年 16:满五年 32:近公园)
     */
    private String[] tagsName;
    /**
     * 房源照片
     */
    private String[] housePhoto;
    /**
     * 房源总价
     */
    private Double houseTotalPrices;
    /**
     * 建筑面积
     */
    private Double buildArea;
    /**
     * 车位配比
     */
    private String carPositionRatio;

    /**
     * 地铁筛选key值，前端使用
     */
    private String subwayKeys;
}
