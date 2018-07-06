package com.toutiao.app.domain.compared;

import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.util.Map;

@Data
public class HouseComparedDetailDo {

    /**
     * id
     */
    private Integer id;
    /**
     * 二手房房源id
     */
    private String houseId;

    /**
     * 房源标题
     */
    private String houseTitle;

    /**
     * 房源总价
     */
    private Double houseTotalPrices;

    /**
     * 建筑面积
     */
    private Double buildArea;

    /**
     * 标题图
     */
    private String housePhotoTitle;

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
     * 标签名称(1:近地铁 4:随时看 8:满二年 16:满五年 32:近公园)
     */
    @ChangeName("tags")
    private String[] tagsName;

    /**
     * 区域
     */
    @ChangeName("districtName")
    private String area;

    /**
     * 商圈
     */
    @ChangeName("areaName")
    private String houseBusinessName;

    /**
     * 朝向名称(1:东,2:西,3:南,4:北,5:东南,6:西南,7:东北,8:西北,9:东西,10:南北)
     */
    @ChangeName("forward")
    private String forwardName;

    /**
     * 建成年代
     */
    @ChangeName("buildYears")
    private String year;

    /**
     * 地铁到房源的距离
     */
    @ChangeName("nearbyStationsDistance")
    private Map subwayDistince;

    /**
     * 总楼层
     */
    private String totalFloor;

    /**
     * 房源所在层
     */
    private String floorNo;

    /**
     * 坐标
     */
    private String location;

    /**
     * 楼盘ID
     */
    private String newcode;

    /**
     * 楼盘名称
     */
    @ChangeName("buildingName")
    private String plotName;

    /**
     * 户均绿化率
     */
    private Double avgGreening;

    /**
     * 户均电梯
     */
    private String avgElevator;

    /**
     * 物业费
     */
    private String propertyFee;

    /**
     * 车位配比
     */
    private String carPositionRatio;

    /**
     * 停车费
     */
    private String parkingRate;

    /**
     * 电费
     */
    private Double electricFee;

    /**
     * 水费
     */
    private Double waterFee;

    /**
     * 供暖方式
     */
    private String heatingMode;

    /**
     * 空气质量
     */
    private String airQuality;

    /**
     * 是否主力户型(0-否，1-是)
     */
    private Integer isMainLayout;

    /**
     * 是否降价房(0-否，1-降价房，2-涨价房)
     */
//    @ChangeName("isCutPrice")
    private Integer priceIncreaseDecline;

    /**
     * 总价价格浮动
     */
//    @ChangeName("priceFloat")
    private Double priceIncreaseDeclineAmount;


    /**
     * 是否是top50小区房源(0-否，1-是)
     */
    private Integer isCommunityTopHouse;

    /**
     * 是否同户型小区均价最低(0-否，1-是)
     */
    private Integer isLowest;
}