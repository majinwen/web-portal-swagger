package com.toutiao.app.domain.sellhouse;

import lombok.Data;

@Data
public class ChooseSellHouseDo {

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


    /**
     * 区域
     */

    private  String area;

    /**
     * 商圈
     */
    private  String houseBusinessName;


    /**
     * 均价
     */
    private  Double houseUnitCost;
}
