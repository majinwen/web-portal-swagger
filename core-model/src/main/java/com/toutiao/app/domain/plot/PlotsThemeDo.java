package com.toutiao.app.domain.plot;

import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PlotsThemeDo {
    /**
     * 小区编号
     */
    @ChangeName("buildingId")
    private Integer id;

    /**
     * 小区名称
     */
    @ChangeName("buildingName")
    private String rc;

    /**
     * 小区照片
     */
    @ChangeName("buildingImages")
    private String[] photo;

    /**
     * 区域编号
     */
    @ChangeName("districtId")
    private String areaId;

    /**
     * 区域
     */
    @ChangeName("districtName")
    private String area;

    /**
     * 商圈编号
     */
    @ChangeName("areaId")
    private String tradingAreaId;

    /**
     * 商圈
     */
    @ChangeName("areaName")
    private String tradingArea;

    /**
     * 地址
     */
    private String address;

//    /**
//     * 坐标
//     */
//    private String location;

    /**
     * 绿化率
     */
    private String avgGreening;

//    /**
//     * 地铁站编号
//     */
//    @ChangeName("subwayStationId")
//    private String[] metroStationId;

//    /**
//     * 地铁站
//     */
//    @ChangeName("subwayStationName")
//    private String[] metroStation;

//    /**
//     * 地铁站与小区的距离
//     */
//    @ChangeName("nearbyStationsDistance")
//    private Map metroWithPlotsDistance;

    /**
     * 近地铁描述
     */
    private String trafficInformation;

//    /**
//     * 建筑面积
//     */
//    private Double buildingAreaSize;

    /**
     * 开发商
     */
    private String developers;

    /**
     * 均价
     */
    @ChangeName("averagePrice")
    private Double avgPrice;

    /**
     * 总价
     */
    @ChangeName("totalPrice")
    private Double sumPrice;

    /**
     * 建成年代
     */
    private String abbreviatedAge;

//    /**
//     * 楼龄
//     */
//    private Integer age;

    /**
     * 在售房源套数
     */
    private Integer houseCount;

    /**
     * 距离最近的大型公园
     */
    private String nearestPark;

    /**
     * 距最近公园的距离
     */
    private Double nearestParkDistance;

//    /**
//     * 小区推荐标签Id
//     */
//    private Integer[] recommendBuildTagsId;
//
//    /**
//     * 小区推荐标签名称
//     */
//    private String[] recommendBuildTagsName;

//    /**
//     * 是否top50小区
//     */
//    private Integer isTop;

    /**
     * 最低价
     */
    private Double lowestPrice;

    /**
     * 在商圈种排名
     */
    private Integer rankAvgInBizcircle;

    /**
     * 最小面积
     */
    private Double houseMinArea;

    /**
     * 最大面积
     */
    private  Double houseMaxArea;

    /**
     * 车位配比
     */
    private String carPositionRatio;

    /**
     * 推荐理由
     */
    private CommunityReviewDo recommendReason;

    /**
     * 租房个数
     */
    private Integer rentCount;

    /**
     * 容积率
     */
    private Double dimension;

    /**
     * 标签名称
     */
    private String tagsName;


}
