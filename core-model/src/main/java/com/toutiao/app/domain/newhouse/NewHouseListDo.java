package com.toutiao.app.domain.newhouse;

import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.util.Map;

@Data
public class NewHouseListDo  {

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
    @ChangeName("buildingName")
    private String buildingName;

    /**
     * 楼盘id
     */
    @ChangeName("buildingId")
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
    @ChangeName("nearBySubway")
    private  String roundStation;

    /**
     * 最近交房
     */

    private  String deliverTime;

    /**
     * 车位配比
     */
    @ChangeName("parkRatio")
    private String parkRadio;

    /**
     * 均价
     */
    private Double averagePrice;

    /**
     * 销售状态
     */
    @ChangeName("saleStatus")
    private  String  saleStatusName;

    /**
     * 大楼标题图
     */
    private  String buildingTitleImg;


    private  long roomTotalCount;

    /**
     * 新房收藏数量
     */
    @ChangeName("favoriteCount")
    private Integer newHouseFavorite;


    /**
     *总价
     */
    @ChangeName("totalPrice")
    private Double  totalPrice;


    /**
     * 类别
     */
    private  String propertyType;


    /**
     *  桥
     */
    private  String  ringRoadName;

    /**
     * 附近地铁信息
     */
    private Map nearbysubway;
    /**
     * 房源与地铁站的距离
     */
    private String subwayDistanceInfo;

}
