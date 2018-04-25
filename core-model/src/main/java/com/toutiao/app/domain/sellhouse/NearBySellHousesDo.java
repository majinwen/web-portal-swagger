package com.toutiao.app.domain.sellhouse;

import lombok.Data;

import java.util.List;

@Data
public class NearBySellHousesDo {

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
     * 二手房房源图片
     */
    private List<String> housePhoto;

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
        * 附近地铁信息
     */
    private  String roundStation;


    /**
     *
     * 车位配比
     */
    private String parkRadio;

    /**
     * 建成年代
     */
    private String year;


    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 页数
     */
    private  Integer pageSize=10;


    /**
     *
     * 城市id
     */
    private Integer cityId;


    /**
     * y坐标
     */
    private Double lat;

    /**
     * x坐标
     */
    private Double lon;


    /**
     * 附近距离
     */
    private Integer distance;


    /**
     * /**
     * 关键字
     */
    private String keyword;


    /**
     * 商圈id
     */
    private Integer areaId;


    /**
     * 区域
     */
    private Integer districtId;



    /**
     * 地铁线Id
     */
    private Integer subwayLineId;

    /**
     * 地铁站Id
     */
    private Integer subwayStationId;


    /**
     *   起始价格
     */

    private Integer beginPrice;

    /**
     * 结束价格
     */

    private  Integer endPrice;


    /**
     * 面积起始值
     */

     private  Integer beginArea;


    /**
     * 面积结束值
     */
    private  Integer endArea;


    /**
     * 楼龄[0-5]
     */
    private String houseYearId;


    /**
     * 居室id
     */
    private  Integer [] layout;


    /**
     * 朝向
     */
    private  Integer []  forward;

    /**
     * 房屋标签
     */
    private  Integer [] houseLabelId;

    /**
     * 认领标识
     */
    private  Integer isClaim;


    /**
     * 地铁最近
     */
    private  String traffic;

    /**
     * 楼盘名称
     */
    private  String plotNameAccurate;


}
