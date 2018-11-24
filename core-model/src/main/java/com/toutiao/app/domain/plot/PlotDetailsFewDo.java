package com.toutiao.app.domain.plot;

import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.util.Map;

@Data
public class PlotDetailsFewDo {
    /**
     * 小区编号
     */
    @ChangeName("buildingId")
    private Integer id;
    /**
     * 小区名称/楼盘名称
     */
    @ChangeName("buildingName")
    private String rc;
    /**
     * 别名
     */
    @ChangeName("buildingNickName")
    private String alias;
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
    /**
     * 坐标
     */
    private String location;
    /**
     * 地铁站编号
     */
    @ChangeName("subwayStationId")
    private String[] metroStationId;
    /**
     * 地铁站
     */
    @ChangeName("subwayStationName")
    private String[] metroStation;
    /**
     * 地铁站与小区的距离
     */
    @ChangeName("nearbyStationsDistance")
    private Map metroWithPlotsDistance;
    /**
     * 地铁线路编号
     */
    @ChangeName("subwayLineId")
    private String[] subwayLineId;
    /**
     * 地铁线路
     */
    @ChangeName("subwayLineName")
    private String[] subwayLine;
    /**
     * 交通信息
     */
    @ChangeName("nearBySubway")
    private String trafficInformation;
    /**
     * 标签编号
     */
    @ChangeName("tagsId")
    private String[] labelId;
    /**
     * 标签
     */
    @ChangeName("tags")
    private String[] label;
    /**
     * 待售房源
     */
    private String[] saleHouse;
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
    @ChangeName("buildYears")
    private String abbreviatedAge;
    /**
     * 车位配比
     */
    @ChangeName("parkRatio")
    private String carPositionRatio;
    /**
     * 小区总个数
     */
    private Integer totalNum;
    /**
     * 小区下二手房总数
     */
    private Integer sellHouseTotalNum;
    /**
     * 小区下租房总数
     */
    private Integer rentTotalNum;
    /**
     * 物业类型
     */
    @ChangeName("propertyTypeId")
    private String propertyType;
    /**
     * 物业类型名称
     */
    @ChangeName("propertyType")
    private String propertyTypeName;
    /**
     * 房源与地铁站的距离
     */
    private String subwayDistanceInfo;
    /**
     * 标题图
     */
    private String titlePhoto;

    /**
     * 附近距离
     */
    private String nearbyDistance;
}
