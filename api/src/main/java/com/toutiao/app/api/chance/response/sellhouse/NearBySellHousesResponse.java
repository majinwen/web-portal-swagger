package com.toutiao.app.api.chance.response.sellhouse;

import lombok.Data;

import java.util.List;

@Data
public class NearBySellHousesResponse {


    /**
     * 二手房房源id
     */
    private String houseId;

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
     * 卫
     */
    private Integer toilet;

    /**
     * 二手房房源标题图
     */
    private String housePhotoTitle;

    /**
     * 标签名称(1:近地铁 4:随时看 8:满二年 16:满五年 32:近公园)
     */
    private String[] tagsName;

    /**
     * 房源总价
     */
    private Double houseTotalPrices;

    /**
     * 建筑面积
     */
    private Double buildArea;

    /**
     * 朝向名称(1:东,2:西,3:南,4:北,5:东南,6:西南,7:东北,8:西北,9:东西,10:南北)
     */
    private String forwardName;


    /**
     * 二手房房源图片
     */
    private List<String> housePhoto;

    /**
     *
     * 认领标识
     */
    private  Integer isClaim;


    /**
     * 建成年代
     */
    private String year;

    /**
     * 地铁最近
     */
    private  String traffic;

    /**
     * 楼盘名称
     */
    private  String plotNameAccurate;


    /**
     * 车辆配比
     */
    private  String parkRadio;


}
