package com.toutiao.app.api.chance.response.newhouse;

import lombok.Data;

@Data
public class NewHosueListResponse {

    /**
     * 最小面积
     */
    private Double houseMinArea;

    /**
     * 最大面积
     */
    private  Double houseMaxArea;

    /**
     * 楼盘名称
     */
    private String buildingName;

    /**
     * 楼盘id
     */
     private  Integer buildingNameId;

    /**
     * 区域名字
     */
    private  String  districtName;

    /**
     * 区域id
     */
    private Integer districtId;

    /**
     * 地铁信息
     */
    private  String roundStation;

    /**
     * 最近交房
     */

    private  String deliverTime;


    /**
     * 车位配比
     */
    private String parkRadio;

    /**
     * 均价
     */
    private  String averagePrice;

    /**
     * 销售状态
     */
    private  String  saleStatusName;

    /**
     * 大楼标题图
     */
    private  String buildingTitleImg;


    private  long roomTotalCount;






}
