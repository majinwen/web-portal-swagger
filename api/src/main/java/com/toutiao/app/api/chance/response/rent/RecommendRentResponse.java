package com.toutiao.app.api.chance.response.rent;

import lombok.Data;


@Data
public class RecommendRentResponse {


    /**
     * 出租房源Id
     */
    private String houseId;

    /**
     * 租金(元/月)
     */
    private Double rentHousePrice;

    /**
     * 租赁方式名称
     */
    private String rentTypeName;
    /**
     * 房源面积
     */
    private Double houseArea;
    /**
     * 几室
     */
    private String room;
    /**
     * 几厅
     */
    private Integer hall;
    /**
     * 朝向（1-东,2-西,3-南,4-北,5-东南,6-西南,7-东北,8-西北,9-东西,10-南北,11-其他）
     */
    private String forward;

    /**
     * 区域名称
     */
    private String districtName;

    /**
     * 商圈名称
     */
    private String areaName;

    /**
     * 小区名称
     */
    private String zufangName;

    /**
     * 出租房源标签名称
     */
    private String[] rentHouseTagsName;

    /**
     * 推优查询id
     */
    private String uid;

    /**
     * 录入/导入房源标题
     */
    private String houseTitle;

    /**
     * 房源标题图
     */
    private String houseTitleImg;

}
